package com.example.exchangeapp;

import android.content.Context;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesRecViewAdapter extends RecyclerView.Adapter<FavoritesRecViewAdapter.ViewHolder>{

    private ArrayList<DailyPrice> dailyPrices= new ArrayList<>();
    public FavoritesRecViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textSymbol.setText(dailyPrices.get(position).getSymbol());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(this,dailyPrices.get(position).getSymbol() + "Selected",Toast.LENGTH_SHORT).show();

            }
        });

        double value = dailyPrices.get(position).getDailyOpen();
        String s = Double.toString(value);
        double value1 = dailyPrices.get(position).getDailyClose();
        String s1 = Double.toString(value1);

        holder.OpenText1.setText(s);
        holder.CloseText.setText(s1);

        if(dailyPrices.get(position).isExpanded()) {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.dropArrow.setVisibility(View.GONE);
        } else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.dropArrow.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return dailyPrices.size();
    }

    public void setDailyPrices(ArrayList<DailyPrice> dailyPrices) {
        this.dailyPrices = dailyPrices;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView parent;
        private TextView textSymbol;
        private ImageView dropArrow, upArrow;
        private RelativeLayout expandedRelLayout;
        private TextView OpenText1, CloseText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            textSymbol = itemView.findViewById(R.id.txtView1);

            dropArrow = itemView.findViewById(R.id.btnDownArrow);
            upArrow = itemView.findViewById(R.id.btnUpArrow);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);
            OpenText1 = itemView.findViewById(R.id.OpenText1);
            CloseText = itemView.findViewById(R.id.CloseText);


            dropArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DailyPrice dailyPrice = dailyPrices.get(getAdapterPosition());
                    dailyPrice.setExpanded(!dailyPrice.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DailyPrice dailyPrice = dailyPrices.get(getAdapterPosition());
                    dailyPrice.setExpanded(!dailyPrice.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
