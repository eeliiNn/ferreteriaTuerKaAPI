using FerreteriaTuerKa.DTOs.Venta;

namespace FerreteriaTuerKa.Services.Interfaces
{
    public interface IVentaService
    {
        Task<IEnumerable<VentaReadDTO>> GetAllAsync();
        Task<VentaReadDTO?> GetByIdAsync(int id);
        Task<VentaReadDTO> CreateAsync(VentaCreateDTO dto);
    }
}
