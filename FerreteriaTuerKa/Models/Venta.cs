using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FerreteriaTuerKa.Models
{
    [Table("venta")]
    public class Venta
    {
        [Key]
        public int VentaId { get; set; }

        public DateTime Fecha { get; set; } = DateTime.Now;

        [Required]
        public decimal Total { get; set; }

        public ICollection<DetalleVenta>? Detalles { get; set; }
    }
}
