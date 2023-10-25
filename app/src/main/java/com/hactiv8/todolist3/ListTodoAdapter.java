package com.hactiv8.todolist3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListTodoAdapter extends RecyclerView.Adapter<ListTodoAdapter.ListViewHolder> {
    private final List<ListToDoClass> todoList;

    public ListTodoAdapter(List<ListToDoClass> todolist, KegiatanListener hapusListener, KegiatanListener ubahListener){
        this.todoList = todolist;
        this.hapusListener = hapusListener;
        this.ubahListener = ubahListener;
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
        return todoList.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private final TextView mtvNamaKegiatan;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            mtvNamaKegiatan = itemView.findViewById(R.id.tvNamaKegiatan);
            ImageButton mbtnUbahKegiatan = itemView.findViewById(R.id.btnUbahKegiatan);
            ImageButton mbtnHapusKegiatan = itemView.findViewById(R.id.btnHapuskegiatan);

            mbtnUbahKegiatan.setOnClickListener(v -> ubahListener.onUbahKegiatan(getAdapterPosition()));

            mbtnHapusKegiatan.setOnClickListener(v -> hapusListener.onHapusKegiatan(getAdapterPosition()));


        }
    }

    public interface KegiatanListener{
        void onHapusKegiatan(int position);
        void onUbahKegiatan(int position);
    }


    private final KegiatanListener hapusListener;
    private final KegiatanListener ubahListener;
}
