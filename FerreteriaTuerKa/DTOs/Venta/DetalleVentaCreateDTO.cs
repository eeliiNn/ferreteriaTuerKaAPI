using System.ComponentModel.DataAnnotations;

namespace FerreteriaTuerKa.DTOs.Venta
{
    public class DetalleVentaCreateDTO
    {
        public int ProductoId { get; set; }
        public int Cantidad { get; set; }
    }
}