using System.ComponentModel.DataAnnotations;

namespace FerreteriaTuerKa.DTOs.Producto
{
    public class ProductoUpdateDTO
    {
        [Required]
        public string Nombre { get; set; } = null!;

        public string? Descripcion { get; set; }

        [Range(0, double.MaxValue)]
        public decimal Precio { get; set; }

        [Range(0, int.MaxValue)]
        public int Stock { get; set; }

        public string? Imagen { get; set; }

        public int? CategoriaId { get; set; }

        public int? ProveedorId { get; set; }
    }
}
