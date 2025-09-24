package com.example.projekt2septembar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterKlasa extends RecyclerView.Adapter<AdapterKlasa.Holder> {
    private final interfejs Interfejs;

    Context context;
    ArrayList<Nazivi> patike;

    public AdapterKlasa(Context context, ArrayList<Nazivi> patike, interfejs Interfejs){

     this.context = context;
     this.patike = patike;
     this.Interfejs = Interfejs;

    }

    @NonNull
    @Override
    public AdapterKlasa.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.oblike_reda_layouta, parent, false);
        return new AdapterKlasa.Holder(view, Interfejs);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKlasa.Holder holder, int position) {

        holder.slika.setImageResource(patike.get(position).getSlika());
        holder.Naziv.setText(patike.get(position).getNaziv());
        holder.Cijena.setText(patike.get(position).getCijena());

    }

    @Override
    public int getItemCount() {
        return patike.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{

    ImageView slika;

    TextView Naziv, Cijena;

        public Holder(@NonNull View itemView, interfejs Interfejs) {
            super(itemView);

            slika = itemView.findViewById(R.id.imageView);
            Naziv = itemView.findViewById(R.id.textView3);
            Cijena = itemView.findViewById(R.id.textView5);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  if(Interfejs != null){

                     int pos = getAdapterPosition();

                     if(pos != RecyclerView.NO_POSITION){

                         Interfejs.klikNaItem(pos);

                     }

                  }

                }
            });

        }
    }

}
