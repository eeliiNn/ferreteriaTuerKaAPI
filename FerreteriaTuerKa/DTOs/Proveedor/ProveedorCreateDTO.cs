using System.ComponentModel.DataAnnotations;

namespace FerreteriaTuerKa.DTOs.Proveedor
{
    public class ProveedorCreateDTO
    {
        [Required]
        public string Nombre { get; set; }

        [Required]
        [EmailAddress]
        public string Email { get; set; }

        public string Telefono { get; set; }

        public string Direccion { get; set; }
    }
}
