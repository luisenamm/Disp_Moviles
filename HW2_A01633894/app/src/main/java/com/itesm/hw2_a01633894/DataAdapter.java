package com.itesm.hw2_a01633894;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


import com.example.intentssqlite.R;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.UserViewHolder> {





    ArrayList<String> userInfo;

    public DataAdapter(ArrayList<String> userInfo) {

        this.userInfo = userInfo;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {

        holder.assignData(userInfo.get(position));


    }
    @Override
    public int getItemCount() {

        return userInfo.size();
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {

        TextView data;

        public UserViewHolder(View itemView) {
            super(itemView);
            data = (TextView) itemView.findViewById(R.id.data);
        }

        public void assignData(String datos){

            data.setText(datos);
        }
    }
}