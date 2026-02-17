using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FerreteriaTuerKa.Models
{
    [Table("categoria")]
    public class Categoria
    {
        [Key]
        public int CategoriaId { get; set; }

        [Required]
        [StringLength(100)]
        public string Nombre { get; set; } = null!;

        public ICollection<Producto>? Productos { get; set; }
    }
}
