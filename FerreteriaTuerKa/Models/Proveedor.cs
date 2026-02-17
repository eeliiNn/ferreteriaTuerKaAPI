using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;


namespace FerreteriaTuerKa.Models
{
    [Table("proveedor")]
    public class Proveedor
    {
        [Key]
        public int ProveedorId { get; set; }

        [Required]
        [StringLength(100)]
        public string Nombre { get; set; } = null!;

        [StringLength(20)]
        public string? Telefono { get; set; }

        [EmailAddress]
        [StringLength(100)]
        public string? Email { get; set; }

        [StringLength(200)]
        public string? Direccion { get; set; }

        public ICollection<Producto>? Productos { get; set; }
    }
}
