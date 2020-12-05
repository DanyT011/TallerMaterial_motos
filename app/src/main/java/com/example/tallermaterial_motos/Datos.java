package com.example.tallermaterial_motos;

import java.util.ArrayList;

public class Datos {
    private static ArrayList<Moto> motos = new ArrayList();

    public static void guardar(Moto m){
        motos.add(m);
    }

    public static ArrayList<Moto> obtener(){return motos;}

    public static void eliminar(Moto m){
        for (int i = 0; i < motos.size(); i++) {
            if(motos.get(i).getPlacas().equals(m.getPlacas())){
                motos.remove(i);
                break;
            }
        }
    }
}
