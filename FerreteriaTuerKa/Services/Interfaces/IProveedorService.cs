using FerreteriaTuerKa.DTOs.Proveedor;

namespace FerreteriaTuerKa.Services.Interfaces
{
    public interface IProveedorService
    {
        Task<IEnumerable<ProveedorResponseDTO>> GetAllAsync();
        Task<ProveedorResponseDTO?> GetByIdAsync(int id);
        Task<ProveedorResponseDTO> CreateAsync(ProveedorCreateDTO dto);
        Task<bool> UpdateAsync(int id, ProveedorUpdateDTO dto);
        Task<bool> DeleteAsync(int id);
    }
}
