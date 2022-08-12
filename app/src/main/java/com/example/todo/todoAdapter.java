package com.example.todo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class todoAdapter extends RecyclerView.Adapter <todoAdapter.MyViewHolder> {

    Context context;
    ArrayList<todo> arrayList;

    public todoAdapter(Context c, ArrayList<todo> list) {
        context = c;
        arrayList = list;
    }

    @NonNull
    @Override
    public  MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.desctitle.setText(arrayList.get(position).getDesctitle());
        holder.desctodo.setText(arrayList.get(position).getDesctodo());
        holder.descdate.setText(arrayList.get(position).getDescdate());


        final String getTitle = arrayList.get(position).getDesctitle();
        final String getTodo = arrayList.get(position).getDesctodo();
        final String getDate = arrayList.get(position).getDescdate();
        final String getSave = arrayList.get(position).getEditsave();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,EditToDo.class);
                intent.putExtra("desctitle",getTitle);
                intent.putExtra("desctodo",getTodo);
                intent.putExtra("descdate",getDate);
                intent.putExtra("editsave",getSave);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView desctitle,desctodo,descdate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            desctitle = (TextView) itemView.findViewById(R.id.desctitle);
            desctodo = (TextView)  itemView.findViewById(R.id.desctodo);
            descdate = (TextView) itemView.findViewById(R.id.descdate);


        }
    }

}