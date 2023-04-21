package com.example.lab5_ex2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {
    private ArrayList<Event> eventList = new ArrayList<>();

    public MyAdapter(ArrayList<Event> eventList){
        this.eventList = eventList;
    }
    @NonNull
    @Override
    public MyAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.myViewHolder holder, int position) {
        Event event = eventList.get(position);
        holder.tvName.setText(event.getName());
        holder.tvPlace.setText(event.getPlace());
        holder.tvDate.setText(event.getDate());
        holder.tvTime.setText(event.getTime());
        holder.eventSwitch.setChecked(event.isChecked());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvTime, tvPlace, tvDate;
        private Switch eventSwitch;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            tvDate = itemView.findViewById(R.id.tvDate);
            eventSwitch = itemView.findViewById(R.id.eventSwitch);

            eventSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eventList.get(getAdapterPosition()).setCheck(!eventList.get(getAdapterPosition()).isChecked());
                }
            });
        }
    }
}
