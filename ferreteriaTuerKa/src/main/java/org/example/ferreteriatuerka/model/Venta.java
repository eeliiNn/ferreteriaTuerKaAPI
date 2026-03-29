package org.example.ferreteriatuerka.model;


public class Venta {
    private int ventaId;
    private String emailComprador;
    private String fecha;
    private double total;
    private int productoId;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;;


    public Venta(){}

    public Venta(int ventaId, String emailComprador, String fecha, double total,  int productoId, String nombreProducto,
                 int cantidad, double precioUnitario){
        this.ventaId = ventaId;
        this.emailComprador = emailComprador;
        this.fecha = fecha;
        this.total = total;
        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getVentaId(){ return ventaId; }
    public void setVentaId(int ventaId){ this.ventaId = ventaId; }

    public String getEmailComprador(){ return emailComprador; }
    public void setEmailComprador(String emailComprador){ this.emailComprador = emailComprador; }

    public String getFecha(){ return fecha; }
    public void setFecha(String fecha){ this.fecha = fecha; }

    public double getTotal(){ return total; }
    public void setTotal(double total){ this.total = total; }

    public int getProductoId(){ return productoId; }
    public void setProductoId(int productoId){ this.productoId = productoId; }

    public String getNombreProducto(){ return nombreProducto; }
    public void setNombreProducto(String nombreProducto){ this.nombreProducto = nombreProducto; }

    public int getCantidad(){ return cantidad; }
    public void setCantidad(int cantidad){ this.cantidad = cantidad; }

    public double getPrecioUnitario(){ return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario){ this.precioUnitario = precioUnitario; }
}

