package org.example.ferreteriatuerka.model;

public class DetalleVenta {
    private int detalleVentaId;
    private int ventaId;
    private int productoId;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public DetalleVenta(){}
    public DetalleVenta(int detalleVentaId, int ventaId, int productoId, String nombreProducto,
                        int cantidad, double precioUnitario, double subtotal, String emailCliente){
        this.detalleVentaId = detalleVentaId;
        this.ventaId = ventaId;
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }


    public int getProductoId(){ return productoId; }
    public void setProductoId(int productoId){ this.productoId = productoId; }

    public String getNombreProducto(){ return nombreProducto; }
    public void setNombreProducto(String nombreProducto){ this.nombreProducto = nombreProducto; }

    public int getCantidad(){ return cantidad; }
    public void setCantidad(int cantidad){ this.cantidad = cantidad; }

    public double getPrecioUnitario(){ return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario){ this.precioUnitario = precioUnitario; }

    public double getSubtotal(){ return subtotal; }
    public void setSubtotal(double subtotal){ this.subtotal = subtotal; }
}