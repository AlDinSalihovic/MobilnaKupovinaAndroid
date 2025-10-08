package com.example.projekt2september;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.Holder> {
    private final interface Interface;

    Context context;
    ArrayList<Names> sneakers;

    public AdapterClass(Context context, ArrayList<Names> sneakers, interface Interface){

     this.context = context;
     this.sneakers = sneakers;
     this.Interface = Interface;

    }

    @NonNull
    @Override
    public AdapterClass.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.oblike_reda_layouta, parent, false);
        return new AdapterClass.Holder(view, Interface);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.Holder holder, int position) {

        holder.picture.setImageResource(sneakers.get(position).getPicture());
        holder.name.setText(sneakers.get(position).getName());
        holder.price.setText(sneakers.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return sneakers.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{

    ImageView picture;

    TextView name, price;

        public Holder(@NonNull View itemView, interface Interface) {
            super(itemView);

            picture = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.textView3);
            price = itemView.findViewById(R.id.textView5);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  if(Interface != null){

                     int pos = getAdapterPosition();

                     if(pos != RecyclerView.NO_POSITION){

                         Interface.clikcOnItem(pos);

                     }

                  }

                }
            });

        }
    }

}
