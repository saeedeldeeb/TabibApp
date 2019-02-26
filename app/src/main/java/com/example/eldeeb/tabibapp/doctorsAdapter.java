package com.example.eldeeb.tabibapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class doctorsAdapter extends RecyclerView.Adapter<doctorsAdapter.doctorsViewHolder> {
    private Context context;
    private List<doctors> list;

    public doctorsAdapter(Context context, List<doctors> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public doctorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.doctor_list,null);
        return new doctorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull doctorsViewHolder holder, int position) {
        doctors docs = list.get(position);
        //loading image
        Picasso.with(context)
                .load(docs.getDoctor_image())
                .resize(100,80)
                .centerCrop()
                .into(holder.docImage);


        holder.docName.setText("Doctor:"+docs.getDoctor_name());
        holder.docAddress.setText(docs.getGovernarator()+": "+docs.getRegion());
        holder.price.setText(String.valueOf(docs.getPrice())+"LE.");
        holder.docDescription.setText(docs.getDoctor_description());
        holder.ratingBar.setRating((float) docs.getRating());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class doctorsViewHolder extends RecyclerView.ViewHolder{
        ImageView docImage;
        TextView docName,docAddress,price,docDescription;
        RatingBar ratingBar;

        public doctorsViewHolder(View itemView) {
            super(itemView);

            docImage = itemView.findViewById(R.id.docimage);
            docName = itemView.findViewById(R.id.textViewName);
            docAddress = itemView.findViewById(R.id.textViewaddress);
            price = itemView.findViewById(R.id.textViewPrice);
            docDescription = itemView.findViewById(R.id.textViewdesc);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
