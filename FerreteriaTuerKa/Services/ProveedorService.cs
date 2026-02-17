using FerreteriaTuerKa.DAL;
using FerreteriaTuerKa.DTOs.Proveedor;
using FerreteriaTuerKa.Models;
using FerreteriaTuerKa.Services.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace FerreteriaTuerKa.Services
{
    public class ProveedorService : IProveedorService
    {
        private readonly FerreteriaContext _context;

        public ProveedorService(FerreteriaContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<ProveedorResponseDTO>> GetAllAsync()
        {
            return await _context.Proveedores
                .Select(p => new ProveedorResponseDTO
                {
                    ProveedorId = p.ProveedorId,
                    Nombre = p.Nombre,
                    Email = p.Email,
                    Telefono = p.Telefono,
                    Direccion = p.Direccion
                })
                .ToListAsync();
        }

        public async Task<ProveedorResponseDTO?> GetByIdAsync(int id)
        {
            var proveedor = await _context.Proveedores.FindAsync(id);

            if (proveedor == null)
                return null;

            return new ProveedorResponseDTO
            {
                ProveedorId = proveedor.ProveedorId,
                Nombre = proveedor.Nombre,
                Email = proveedor.Email,
                Telefono = proveedor.Telefono,
                Direccion = proveedor.Direccion
            };
        }

        public async Task<ProveedorResponseDTO> CreateAsync(ProveedorCreateDTO dto)
        {
            var proveedor = new Proveedor
            {
                Nombre = dto.Nombre,
                Email = dto.Email,
                Telefono = dto.Telefono,
                Direccion = dto.Direccion
            };

            _context.Proveedores.Add(proveedor);
            await _context.SaveChangesAsync();

            return new ProveedorResponseDTO
            {
                ProveedorId = proveedor.ProveedorId,
                Nombre = proveedor.Nombre,
                Email = proveedor.Email,
                Telefono = proveedor.Telefono,
                Direccion = proveedor.Direccion
            };
        }

        public async Task<bool> UpdateAsync(int id, ProveedorUpdateDTO dto)
        {
            var proveedor = await _context.Proveedores.FindAsync(id);

            if (proveedor == null)
                return false;

            proveedor.Nombre = dto.Nombre;
            proveedor.Email = dto.Email;
            proveedor.Telefono = dto.Telefono;
            proveedor.Direccion = dto.Direccion;

            await _context.SaveChangesAsync();
            return true;
        }

        public async Task<bool> DeleteAsync(int id)
        {
            var proveedor = await _context.Proveedores.FindAsync(id);

            if (proveedor == null)
                return false;

            _context.Proveedores.Remove(proveedor);
            await _context.SaveChangesAsync();
            return true;
        }
    }
}
