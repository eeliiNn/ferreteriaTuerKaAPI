namespace FerreteriaTuerKa.DTOs.Venta
{
    public class VentaDetalleResponseDTO
    {
        public string ProductoNombre { get; set; } = null!;

        public int Cantidad { get; set; }

        public decimal PrecioUnitario { get; set; }

        public decimal Subtotal { get; set; }
    }
}
