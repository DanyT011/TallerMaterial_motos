package com.example.tallermaterial_motos;

public class Moto {
    private String modelo, marca, placas, id;

    public Moto(String modelo, String marca, String placas, String id) {
        this.modelo = modelo;
        this.marca = marca;
        this.placas = placas;
        this.id = id;
    }

    public Moto(){}

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void guardar(){
        Datos.guardar(this);
    }

    public void eliminar(){
        Datos.eliminar(this);
    }

}
