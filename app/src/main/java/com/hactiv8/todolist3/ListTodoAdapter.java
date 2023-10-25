package com.hactiv8.todolist3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListTodoAdapter extends RecyclerView.Adapter<ListTodoAdapter.ListViewHolder> {
    private List<ListToDoClass> todoList;
    private Context context;

    public ListTodoAdapter(Context context,List<ListToDoClass> todolist){
        this.context = context;
        this.todoList = todolist;
    }

    public ListTodoAdapter(Context context,List<ListToDoClass> todolist, hapusKegiatanListener hapusListener){
        this.context = context;
        this.todoList = todolist;
        this.hapusListener = hapusListener;
    }


    @NonNull
    @Override
    public ListTodoAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTodoAdapter.ListViewHolder holder, int position) {
        ListToDoClass todolist = todoList.get(position);
        holder.mtvNamaKegiatan.setText(todolist.getNameListToDo());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView mtvNamaKegiatan;
        private ImageButton mbtnHapusKegiatan;
        private ImageButton mbtnUbahKegiatan;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            mtvNamaKegiatan = itemView.findViewById(R.id.tvNamaKegiatan);
            mbtnUbahKegiatan = itemView.findViewById(R.id.btnUbahKegiatan);
            mbtnHapusKegiatan = itemView.findViewById(R.id.btnHapuskegiatan);

            mbtnUbahKegiatan.setOnClickListener(v -> {

            });

            mbtnHapusKegiatan.setOnClickListener(v -> {
                hapusListener.onHapusKegiatan(getAdapterPosition());
            });


        }
    }

    public interface hapusKegiatanListener{
        void onHapusKegiatan(int position);
    }

    private hapusKegiatanListener hapusListener;
}
