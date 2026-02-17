using FerreteriaTuerKa.DTOs.Producto;

namespace FerreteriaTuerKa.Services.Interfaces
{
    public interface IProductoService
    {
        Task<IEnumerable<ProductoResponseDTO>> GetAllAsync();

        Task<ProductoResponseDTO?> GetByIdAsync(int id);

        Task<ProductoResponseDTO> CreateAsync(ProductoCreateDTO dto);

        Task<bool> UpdateAsync(int id, ProductoUpdateDTO dto);

        Task<bool> DeleteAsync(int id);
    }
}
