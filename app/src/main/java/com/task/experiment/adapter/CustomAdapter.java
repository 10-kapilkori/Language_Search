package com.task.experiment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.task.experiment.R;
import com.task.experiment.model.PostData;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private static final String TAG = "CustomAdapter";

    Context context;
    JsonObject[] data;
    ItemTapped listener;

    public CustomAdapter(Context context, JsonObject[] data, ItemTapped listener) {
        this.context = context;
        this.data = data;
        this.listener = listener;
    }

    public void setUpdatedList(JsonObject[] data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void updatedData(PostData postData) {
        Log.i(TAG, "updatedData: " + postData.getPosition());
        notifyItemChanged(postData.getPosition(), postData.getName());
        notifyItemChanged(postData.getPosition(), postData.getOwner());
        notifyItemChanged(postData.getPosition(), postData.getDesc()); 
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.custom_adapter, parent, false);
        return new CustomViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        String full_name = data[position].get("full_name").toString().replace("\"", "");
        String owner = data[position].get("owner").getAsJsonObject().get("login").toString().replace("\"", "");
        String desc = data[position].get("description").toString().replace("\"", "");

        holder.nameTv.setText("Name:- " + full_name);
        holder.ownerTv.setText("Login:- " + owner);
        holder.descTv.setText("Description:- " + desc);

        holder.itemView.setOnClickListener(v -> {
            int item_position = holder.getAdapterPosition();
            if (listener != null && item_position != RecyclerView.NO_POSITION) {
                listener.onTapped(item_position, full_name, owner, desc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public interface ItemTapped {
        void onTapped(int position, String name, String owner, String desc);
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTv, ownerTv, descTv;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.nameTv);
            ownerTv = itemView.findViewById(R.id.ownerTv);
            descTv = itemView.findViewById(R.id.descTv);
        }
    }
}
