package com.bichi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bichi.R;
import com.bichi.model.User;

import java.util.List;

public class UserAdapter extends ListAdapter<User,UserAdapter.MyViewHolder> {

    public UserAdapter(){
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<User> DIFF_CALLBACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getEmail() == newItem.getEmail();
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.getEmail().equals(newItem.getEmail())&&
                    oldItem.getPassword().equals(newItem.getPassword());
        }
    };

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User currentNote = getItem(position);

        holder.userId.setText(currentNote.getEmail());
        holder.password.setText(currentNote.getPassword());

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView userId;
        public TextView password;


        public MyViewHolder(View view) {
            super(view);
            userId = view.findViewById(R.id.userId);
            password = view.findViewById(R.id.password);
        }
    }

}
