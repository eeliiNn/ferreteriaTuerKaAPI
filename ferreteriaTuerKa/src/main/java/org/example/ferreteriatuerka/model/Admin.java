package org.example.ferreteriatuerka.model;

public class Admin {

    private int usuarioId;
    private String nombre;
    private String email;
    private String password;

    public Admin(){}

    public Admin(int UsuarioId, String Nombre, String Email, String Password){
        this.usuarioId = UsuarioId;
        this.nombre = Nombre;
        this.email = Email;
        this.password = Password;
    }
    public Admin(String Email, String Password){
        this.email = Email;
        this.password = Password;
    }


    public int getUsuarioId(){ return usuarioId; }
    public void setUsuarioId(int UsuarioId){ this.usuarioId = UsuarioId; }

    public String getNombre(){ return nombre; }
    public void setNombre(String Nombre){ this.nombre = Nombre; }

    public String getEmail(){ return email; }
    public void setEmail(String Email){ this.email = Email; }

    public void setPassword(String Password){ this.password = Password; }
    public String getPassword(){ return password; }
}