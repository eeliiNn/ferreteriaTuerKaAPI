package org.example.ferreteriatuerka.model;

public class Producto {
    private int productoId;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String categoriaNombre;
    private String proveedorNombre;
    private String imagen;
    private Integer categoriaId;
    private Integer proveedorId;

    public Producto(){}

    public Producto(int productoId, String nombre, String descripcion,
                     double precio, int stock, String categoriaNombre, String proveedorNombre,
                    String imagen){
        this.productoId = productoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoriaNombre = categoriaNombre;
        this.proveedorNombre = proveedorNombre;
        this.imagen = imagen;
    }

    //Getters y Setters
    public int getProductoId(){ return productoId; }
    public void setProductoId(int productoId){ this.productoId = productoId; }

    public String getNombre(){ return nombre; }
    public void setNombre(String nombre){ this.nombre = nombre; }

    public String getDescripcion(){ return descripcion; }
    public void setDescripcion(String descripcion){ this.descripcion = descripcion; }

    public double getPrecio(){ return precio; }
    public void setPrecio(double precio){ this.precio = precio; }

    public int getStock(){ return stock; }
    public void setStock(int stock){ this.stock = stock; }

    public String getImagen(){ return imagen; }
    public void setImagen(String imagen){ this.imagen = imagen; }

    public String getCategoriaNombre(){ return categoriaNombre; }
    public void setCategoriaNombre(String categoriaNombre){ this.categoriaNombre = categoriaNombre; }

    public String getProveedorNombre(){ return proveedorNombre; }
    public void setProveedorNombre(String proveedorNombre){ this.proveedorNombre = proveedorNombre; }

    public Integer getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Integer categoriaId) { this.categoriaId = categoriaId; }

    public Integer getProveedorId() { return proveedorId; }
    public void setProveedorId(Integer proveedorId) { this.proveedorId = proveedorId; }
}