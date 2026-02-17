using FerreteriaTuerKa.Models;
using Microsoft.EntityFrameworkCore;

namespace FerreteriaTuerKa.DAL
{
    public class FerreteriaContext : DbContext
    {
        public FerreteriaContext(DbContextOptions<FerreteriaContext> options)
            : base(options)
        {
        }

        public DbSet<Categoria> Categorias => Set<Categoria>();
        public DbSet<Producto> Productos => Set<Producto>();
        public DbSet<Proveedor> Proveedores => Set<Proveedor>();
        public DbSet<Venta> Ventas => Set<Venta>();
        public DbSet<DetalleVenta> DetalleVentas => Set<DetalleVenta>();
    }

}
