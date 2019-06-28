package com.example.workoutproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class BodypartsAdapter extends RecyclerView.Adapter<BodypartsAdapter.ViewHolder> {

    private ArrayList<bodyPart> parts ;
    private Context thisContext ;

    public BodypartsAdapter(Context context, ArrayList<bodyPart> list){
        parts = list;
        thisContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtName ;
        ImageView picView;
        public ViewHolder(@NonNull final View itemView){
            super(itemView);
            txtName = itemView.findViewById(R.id.rowTxtView);
            picView = itemView.findViewById(R.id.rowImgView);
        }
    }

    @NonNull
    @Override
    public BodypartsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.body_parts_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BodypartsAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(position);

        bodyPart temp = parts.get(position) ;

        holder.txtName.setText(temp.getName());
        holder.picView.setImageResource(temp.getPic());


    }

    @Override
    public int getItemCount() {
        return parts.size();

    }

}
