package com.myandroid.boardapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Board> {

    private Context context;
    private List<Board> arrayListBoard;

    public MyAdapter(@NonNull Context context, List<Board> arrayListBoard) {
        super(context, R.layout.list_item, arrayListBoard);

        this.context = context;
        this.arrayListBoard = arrayListBoard;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null, true);

        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvTitle = view.findViewById(R.id.txt_title);
        TextView tvUser = view.findViewById(R.id.txt_user);
        TextView tvDate = view.findViewById(R.id.txt_date);

        tvID.setText(arrayListBoard.get(position).getId());
        tvTitle.setText(arrayListBoard.get(position).getTitle());
        tvUser.setText(arrayListBoard.get(position).getUser());
        tvDate.setText(arrayListBoard.get(position).getDate());

        return view;
    }
}
