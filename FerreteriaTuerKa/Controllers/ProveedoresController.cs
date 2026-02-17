using Microsoft.AspNetCore.Mvc;
using FerreteriaTuerKa.Services.Interfaces;
using FerreteriaTuerKa.DTOs.Proveedor;

namespace FerreteriaTuerKa.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProveedoresController : ControllerBase
    {
        private readonly IProveedorService _service;

        public ProveedoresController(IProveedorService service)
        {
            _service = service;
        }

        [HttpGet]
        public async Task<IActionResult> GetAll()
        {
            return Ok(await _service.GetAllAsync());
        }

        [HttpGet("{id}")]
        public async Task<IActionResult> GetById(int id)
        {
            var proveedor = await _service.GetByIdAsync(id);

            if (proveedor == null)
                return NotFound();

            return Ok(proveedor);
        }

        [HttpPost]
        public async Task<IActionResult> Create(ProveedorCreateDTO dto)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            var proveedor = await _service.CreateAsync(dto);

            return CreatedAtAction(nameof(GetById),
                new { id = proveedor.ProveedorId },
                proveedor);
        }

        [HttpPut("{id}")]
        public async Task<IActionResult> Update(int id, ProveedorUpdateDTO dto)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);

            var updated = await _service.UpdateAsync(id, dto);

            if (!updated)
                return NotFound();

            return Ok();
        }

        [HttpDelete("{id}")]
        public async Task<IActionResult> Delete(int id)
        {
            var deleted = await _service.DeleteAsync(id);

            if (!deleted)
                return NotFound();

            return Ok();
        }
    }
}
