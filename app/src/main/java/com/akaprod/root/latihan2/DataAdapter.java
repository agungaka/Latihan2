package com.akaprod.root.latihan2;


import android.content.Context;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private Context context;
    private List<Data> list;


    //ImageLoader imageLoader = Controller.getPermission().getImageLoader();

    public DataAdapter(Context context, List<Data> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_data_adapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data data = list.get(position);

        //id ini ga perlu ditampilin
        holder.txtFruit.setText(String.valueOf(data.getId_fruit())); //getFruit());
        holder.txtFruit.setVisibility(View.GONE);


        holder.txtTitle.setText(data.getTitle());   //(String.valueOf(data.getTitle()));
        holder.txtDescription.setText(data.getDescription());
        holder.txtCreated.setText(data.getCreated_at());
        holder.txtUpdated.setText(data.getUpdated_at());//setText(data.getFruit());

        //jngan langsung di klik

        if (data.getImage() != null && data.getImage().length() > 0) {
            Picasso.with(context).load(data.getImage()).fit().centerCrop()
                    .placeholder(R.drawable.kid_goku)
                    .error(R.drawable.goku)
                    .into(holder.imgSource);
        } else {
            //Gambar diilangin klo emng ga ada dr API
            holder.imgSource.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtFruit, txtTitle, txtDescription, txtCreated, txtUpdated;
        private ImageView imgSource;

        public ViewHolder(View itemView) {
            super(itemView);

            txtFruit = itemView.findViewById(R.id.txtFruit);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            imgSource = itemView.findViewById(R.id.img);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtUpdated = itemView.findViewById(R.id.txtUpdated);
            txtCreated = itemView.findViewById(R.id.txtCreated);
        }
    }

}