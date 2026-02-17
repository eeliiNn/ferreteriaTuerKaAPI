using FerreteriaTuerKa.DTOs.Producto;
using FerreteriaTuerKa.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

namespace FerreteriaTuerKa.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProductosController : ControllerBase
    {
        private readonly IProductoService _service;

        public ProductosController(IProductoService service)
        {
            _service = service;
        }

        // GET: api/productos
        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            var productos = await _service.GetAllAsync();
            return Ok(productos);
        }

        // GET: api/productos/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetById(int id)
        {
            var producto = await _service.GetByIdAsync(id);

            if (producto == null)
                return NotFound(new { message = "Producto no encontrado." });

            return Ok(producto);
        }

        // POST: api/productos
        [HttpPost]
        public async Task<IActionResult> Create(ProductoCreateDTO dto)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            try
            {
                var producto = await _service.CreateAsync(dto);

                return CreatedAtAction(nameof(GetById),
                    new { id = producto.ProductoId },
                    producto);
            }
            catch (ArgumentException ex)
            {
                return BadRequest(new { message = ex.Message });
            }
        }

        // PUT: api/productos/5
        [HttpPut("{id}")]
        public async Task<IActionResult> Update(int id, ProductoUpdateDTO dto)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            try
            {
                var updated = await _service.UpdateAsync(id, dto);

                if (!updated)
                    return NotFound(new { message = "Producto no encontrado." });

                return Ok(new { message = "Producto actualizado correctamente." });
            }
            catch (ArgumentException ex)
            {
                return BadRequest(new { message = ex.Message });
            }
        }

        // DELETE: api/productos/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            var deleted = await _service.DeleteAsync(id);

            if (!deleted)
                return NotFound(new { message = "Producto no encontrado." });

            return Ok(new { message = "Producto eliminado correctamente." });
        }
    }
}
