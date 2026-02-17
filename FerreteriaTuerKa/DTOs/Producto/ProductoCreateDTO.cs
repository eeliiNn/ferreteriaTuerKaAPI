using System.ComponentModel.DataAnnotations;

namespace FerreteriaTuerKa.DTOs.Producto
{
    public class ProductoCreateDTO
    {
        [Required(ErrorMessage = "El nombre es obligatorio.")]
        public string Nombre { get; set; } = null!;

        public string? Descripcion { get; set; }

        [Range(0, double.MaxValue, ErrorMessage = "El precio no puede ser negativo.")]
        public decimal Precio { get; set; }

        [Range(0, int.MaxValue, ErrorMessage = "El stock no puede ser negativo.")]
        public int Stock { get; set; }

        public string? Imagen { get; set; }

        public int? CategoriaId { get; set; }

        public int? ProveedorId { get; set; }
    }
}
