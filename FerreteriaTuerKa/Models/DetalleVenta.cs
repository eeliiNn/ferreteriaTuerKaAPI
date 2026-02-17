using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace FerreteriaTuerKa.Models
{
    [Table("detalleventas")]
    public class DetalleVenta
    {
        [Key]
        public int DetalleVentaId { get; set; }

        [Required]
        public int VentaId { get; set; }

        public Venta? Venta { get; set; }

        [Required]
        public int ProductoId { get; set; }

        public Producto? Producto { get; set; }

        [Required]
        public int Cantidad { get; set; }

        [Required]
        public decimal PrecioUnitario { get; set; }

        [Required]
        public decimal Subtotal { get; set; }
    }
}
