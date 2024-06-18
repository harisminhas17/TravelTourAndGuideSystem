package com.example.traveltourandguidesystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.traveltourandguidesystem.Models.NotificationModel;
import com.example.traveltourandguidesystem.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    ArrayList<NotificationModel> list = new ArrayList<>();

    Context context;
    boolean isHori;

    public NotificationAdapter(ArrayList<NotificationModel> list, Context context, boolean isHori) {
        this.list = list;
        this.context = context;
        this.isHori = isHori;
    }

    @Override
    public NotificationAdapter.NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new NotificationAdapter.NotificationViewHolder(inflater.inflate(R.layout.notifications_item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final NotificationAdapter.NotificationViewHolder viewHolder, int position) {
        NotificationModel notificationmodel = list.get(position);
        //ToDo link other objects here
        viewHolder.notification_tv.setText(notificationmodel.getText());
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        View view;

        TextView notification_tv;


        NotificationViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            notification_tv = itemView.findViewById(R.id.notification_tv);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}

