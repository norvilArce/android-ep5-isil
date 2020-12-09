package com.example.firebase.models;

public class Mensaje {

    private String titulo;
    private String mensaje;

    public Mensaje() {
    }

    public Mensaje(String titulo, String mensaje) {
        this.titulo = titulo;
        this.mensaje = mensaje;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "titulo='" + titulo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}

