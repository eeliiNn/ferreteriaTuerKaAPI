package org.example.ferreteriatuerka.model;

public class Categoria {
    private int categoriaId;
    private String nombre;

    public int getCategoriaId() { return categoriaId; }
    public String getNombre() { return nombre; }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        return nombre;
    }

}
