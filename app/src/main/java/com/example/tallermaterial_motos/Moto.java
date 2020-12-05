package com.example.tallermaterial_motos;

public class Moto {
    private String modelo, marca, placas;
    private int foto;

    public Moto(String modelo, String marca, String placas, int foto) {
        this.modelo = modelo;
        this.marca = marca;
        this.placas = placas;
        this.foto = foto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void guardar(){
        Datos.guardar(this);
    }

    public void eliminar(){
        Datos.eliminar(this);
    }
}
