package com.example.tallermaterial_motos;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Datos {
    private static String db = "Motos";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private static StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private static ArrayList<Moto> motos = new ArrayList();

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void guardar(Moto m){
        databaseReference.child(db).child(m.getId()).setValue(m);
    }

    public static void setMotos(ArrayList<Moto> motos){motos = motos;}

    public static void eliminar(Moto m){
        databaseReference.child(db).child(m.getId()).setValue(m);
        storageReference.child(m.getId()).delete();
    }
}
