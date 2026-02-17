using FerreteriaTuerKa.DTOs.Venta;
using FerreteriaTuerKa.Services.Interfaces;
using Microsoft.AspNetCore.Mvc;

[Route("api/[controller]")]
[ApiController]
public class VentasController : ControllerBase
{
    private readonly IVentaService _service;

    public VentasController(IVentaService service)
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
        var venta = await _service.GetByIdAsync(id);

        if (venta == null)
            return NotFound();

        return Ok(venta);
    }

    [HttpPost]
    public async Task<IActionResult> Create(VentaCreateDTO dto)
    {
        try
        {
            var venta = await _service.CreateAsync(dto);

            return CreatedAtAction(nameof(GetById),
                new { id = venta.VentaId },
                venta);
        }
        catch (ArgumentException ex)
        {
            return BadRequest(new { message = ex.Message });
        }
    }
}
