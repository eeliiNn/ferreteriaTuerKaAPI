using System.ComponentModel.DataAnnotations;

namespace FerreteriaTuerKa.DTOs.Venta
{
    public class VentaCreateDTO
    {
        public List<DetalleVentaCreateDTO> Detalles { get; set; }
    }
}
