package com.hactiv8.todolist3;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ListTodoAdapter.hapusKegiatanListener{
    private ImageButton mbtnTambah;
    private RecyclerView rvList;

    DataBaseHandler db;
    private List<ListToDoClass> todolist;
    private ListTodoAdapter listTodoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvList = findViewById(R.id.rv_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);

        mbtnTambah = findViewById(R.id.btnTambah);
        mbtnTambah.setOnClickListener(v -> showForm());

        db = new DataBaseHandler(this);

        loadData();
    }

    private void showForm() {
        ListToDoClass todolist = new ListToDoClass(1,"Catatan Pertama Test");
        db.AddListToDo(todolist);
        loadData();
    }

    private void loadData(){
        todolist = db.getAllListToDo();
        listTodoAdapter = new ListTodoAdapter(this,todolist,this);
        rvList.setAdapter(listTodoAdapter);
    }


    @Override
    public void onHapusKegiatan(int position) {
        ListToDoClass selectedHapus = todolist.get(position);
        db.DeleteListToDo(selectedHapus);
        loadData();
    }
}