package com.example.tallermaterial_motos;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorMoto extends  RecyclerView.Adapter<AdaptadorMoto.MotoViewHolder>{
    private ArrayList<Moto> motos;
    private OnMotoClickListener clickListener;

    public AdaptadorMoto(ArrayList<Moto> motos, OnMotoClickListener clickListener){
        this.motos = motos;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_motos,parent,false);
        return new MotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MotoViewHolder holder, int position) {
        Moto m = motos.get(position);
        StorageReference storageReference;
        storageReference = FirebaseStorage.getInstance().getReference();

        storageReference.child(m.getId()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.foto);
            }
        });

        holder.modelo.setText(m.getModelo());
        holder.marca.setText(m.getMarca());
        holder.placa.setText(m.getPlacas());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onMotoClick(m);
            }
        });

    }

    @Override
    public int getItemCount() {
        return motos.size();
    }

    public static class MotoViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView foto;
        private TextView modelo, marca, placa;
        private View v;

        public MotoViewHolder(View itemView){
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.imgFoto);
            modelo = v.findViewById(R.id.lblModelo);
            marca = v.findViewById(R.id.lblMarca);
            placa = v.findViewById(R.id.lblPlaca);
        }
    }

    public interface OnMotoClickListener{
        void onMotoClick(Moto m);
    }
}
