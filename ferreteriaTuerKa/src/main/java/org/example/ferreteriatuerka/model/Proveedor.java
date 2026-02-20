package org.example.ferreteriatuerka.model;

public class Proveedor {
    private int proveedorId;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;

    public Proveedor(){}

    public Proveedor(int proveedorId, String nombre, String telefono,
                    String email, String direccion){
        this.proveedorId = proveedorId;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    //Getters y Setters
    public int getProveedorId(){return proveedorId;}
    public void setProveedorId(int proveedorId){this.proveedorId = proveedorId;}
    public String getNombre(){return nombre;}
    public void setNombre(String nombre){this.nombre = nombre;}
    public String getTelefono(){return telefono;}
    public void setTelefono(String telefono){this.telefono = telefono;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}
    public String getDireccion(){return direccion;}
    public void setDireccion(String direccion){this.direccion = direccion;}

}
