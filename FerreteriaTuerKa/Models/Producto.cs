using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FerreteriaTuerKa.Models
{
    [Table("producto")]
    public class Producto
    {
        [Key]
        public int ProductoId { get; set; }

        [Required]
        [StringLength(150)]
        public string Nombre { get; set; } = null!;

        [StringLength(250)]
        public string? Descripcion { get; set; }

        [Required]
        public decimal Precio { get; set; }

        [Required]
        public int Stock { get; set; }

        public string? Imagen { get; set; }

        public int? CategoriaId { get; set; }
        public Categoria? Categoria { get; set; }

        public int? ProveedorId { get; set; }
        public Proveedor? Proveedor { get; set; }

        public ICollection<DetalleVenta>? Detalles { get; set; }
    }
}
