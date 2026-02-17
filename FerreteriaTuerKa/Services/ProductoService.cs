using FerreteriaTuerKa.DAL;
using FerreteriaTuerKa.DTOs.Producto;
using FerreteriaTuerKa.Models;
using FerreteriaTuerKa.Services.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace FerreteriaTuerKa.Services
{
    public class ProductoService : IProductoService
    {
        private readonly FerreteriaContext _context;

        public ProductoService(FerreteriaContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<ProductoResponseDTO>> GetAllAsync()
        {
            return await _context.Productos
                .Include(p => p.Categoria)
                .Include(p => p.Proveedor)
                .Select(p => new ProductoResponseDTO
                {
                    ProductoId = p.ProductoId,
                    Nombre = p.Nombre,
                    Descripcion = p.Descripcion,
                    Precio = p.Precio,
                    Stock = p.Stock,
                    Imagen = p.Imagen,
                    CategoriaNombre = p.Categoria != null ? p.Categoria.Nombre : null,
                    ProveedorNombre = p.Proveedor != null ? p.Proveedor.Nombre : null
                })
                .ToListAsync();
        }

        public async Task<ProductoResponseDTO?> GetByIdAsync(int id)
        {
            var producto = await _context.Productos
                .Include(p => p.Categoria)
                .Include(p => p.Proveedor)
                .FirstOrDefaultAsync(p => p.ProductoId == id);

            if (producto == null)
                return null;

            return new ProductoResponseDTO
            {
                ProductoId = producto.ProductoId,
                Nombre = producto.Nombre,
                Descripcion = producto.Descripcion,
                Precio = producto.Precio,
                Stock = producto.Stock,
                Imagen = producto.Imagen,
                CategoriaNombre = producto.Categoria?.Nombre,
                ProveedorNombre = producto.Proveedor?.Nombre
            };
        }

        public async Task<ProductoResponseDTO> CreateAsync(ProductoCreateDTO dto)
        {
            // Validación de negocio extra
            if (dto.Precio < 0)
                throw new ArgumentException("El precio no puede ser negativo.");

            if (dto.Stock < 0)
                throw new ArgumentException("El stock no puede ser negativo.");

            var producto = new Producto
            {
                Nombre = dto.Nombre,
                Descripcion = dto.Descripcion,
                Precio = dto.Precio,
                Stock = dto.Stock,
                Imagen = dto.Imagen,
                CategoriaId = dto.CategoriaId,
                ProveedorId = dto.ProveedorId
            };

            _context.Productos.Add(producto);
            await _context.SaveChangesAsync();

            return new ProductoResponseDTO
            {
                ProductoId = producto.ProductoId,
                Nombre = producto.Nombre,
                Descripcion = producto.Descripcion,
                Precio = producto.Precio,
                Stock = producto.Stock,
                Imagen = producto.Imagen
            };
        }

        public async Task<bool> UpdateAsync(int id, ProductoUpdateDTO dto)
        {
            var producto = await _context.Productos.FindAsync(id);

            if (producto == null)
                return false;

            if (dto.Precio < 0)
                throw new ArgumentException("El precio no puede ser negativo.");

            if (dto.Stock < 0)
                throw new ArgumentException("El stock no puede ser negativo.");

            producto.Nombre = dto.Nombre;
            producto.Descripcion = dto.Descripcion;
            producto.Precio = dto.Precio;
            producto.Stock = dto.Stock;
            producto.Imagen = dto.Imagen;
            producto.CategoriaId = dto.CategoriaId;
            producto.ProveedorId = dto.ProveedorId;

            await _context.SaveChangesAsync();
            return true;
        }

        public async Task<bool> DeleteAsync(int id)
        {
            var producto = await _context.Productos.FindAsync(id);

            if (producto == null)
                return false;

            _context.Productos.Remove(producto);
            await _context.SaveChangesAsync();
            return true;
        }
    }
}

