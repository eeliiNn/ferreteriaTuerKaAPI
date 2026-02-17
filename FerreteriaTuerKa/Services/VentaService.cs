using FerreteriaTuerKa.DAL;
using FerreteriaTuerKa.DTOs.Venta;
using FerreteriaTuerKa.Models;
using FerreteriaTuerKa.Services.Interfaces;
using Microsoft.EntityFrameworkCore;

public class VentaService : IVentaService
{
    private readonly FerreteriaContext _context;

    public VentaService(FerreteriaContext context)
    {
        _context = context;
    }

    public async Task<IEnumerable<VentaReadDTO>> GetAllAsync()
    {
        return await _context.Ventas
            .Select(v => new VentaReadDTO
            {
                VentaId = v.VentaId,
                Fecha = v.Fecha,
                Total = v.Total
            })
            .ToListAsync();
    }

    public async Task<VentaReadDTO?> GetByIdAsync(int id)
    {
        var venta = await _context.Ventas.FindAsync(id);

        if (venta == null)
            return null;

        return new VentaReadDTO
        {
            VentaId = venta.VentaId,
            Fecha = venta.Fecha,
            Total = venta.Total
        };
    }

    public async Task<VentaReadDTO> CreateAsync(VentaCreateDTO dto)
    {
        if (dto.Detalles == null || !dto.Detalles.Any())
            throw new ArgumentException("La venta debe tener al menos un producto.");

        using var transaction = await _context.Database.BeginTransactionAsync();

        try
        {
            decimal total = 0;
            var detallesVenta = new List<DetalleVenta>();

            foreach (var item in dto.Detalles)
            {
                var producto = await _context.Productos
                    .FirstOrDefaultAsync(p => p.ProductoId == item.ProductoId);

                if (producto == null)
                    throw new ArgumentException($"Producto {item.ProductoId} no existe.");

                if (producto.Stock < item.Cantidad)
                    throw new ArgumentException($"Stock insuficiente para {producto.Nombre}.");

                var subtotal = producto.Precio * item.Cantidad;
                total += subtotal;

                producto.Stock -= item.Cantidad;

                detallesVenta.Add(new DetalleVenta
                {
                    ProductoId = producto.ProductoId,
                    Cantidad = item.Cantidad,
                    PrecioUnitario = producto.Precio,
                    Subtotal = subtotal
                });
            }

            var venta = new Venta
            {
                Fecha = DateTime.Now,
                Total = total,
                Detalles = detallesVenta
            };

            _context.Ventas.Add(venta);
            await _context.SaveChangesAsync();

            await transaction.CommitAsync();

            return new VentaReadDTO
            {
                VentaId = venta.VentaId,
                Fecha = venta.Fecha,
                Total = venta.Total
            };
        }
        catch
        {
            await transaction.RollbackAsync();
            throw;
        }
    }
}
