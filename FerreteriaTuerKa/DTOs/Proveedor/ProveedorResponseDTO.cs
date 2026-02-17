namespace FerreteriaTuerKa.DTOs.Proveedor
{
    public class ProveedorResponseDTO
    {
        public int ProveedorId { get; set; }

        public string Nombre { get; set; } = null!;

        public string? Email { get; set; }

        public string? Telefono { get; set; }

        public string? Direccion { get; set; }
    }
}