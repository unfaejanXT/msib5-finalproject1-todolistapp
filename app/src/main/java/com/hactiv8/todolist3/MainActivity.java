package com.hactiv8.todolist3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ListTodoAdapter.KegiatanListener {
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
        mbtnTambah.setOnClickListener(v -> showForm(new ListToDoClass(),null));

        db = new DataBaseHandler(this);

        loadData();
    }

    private void showForm(ListToDoClass listToDoClass, String existingNote) {
        AlertDialog.Builder formBuilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.form_note, null);
        formBuilder.setView(view);

        AlertDialog popup = formBuilder.create();
        popup.show();

        EditText etNote = view.findViewById(R.id.etNote);
        Button btnSave = view.findViewById(R.id.save_data);

        if (listToDoClass != null) {
            // Edit mode
            etNote.setText(listToDoClass.getNameListToDo());
        } else {
            // Create mode
            etNote.setText(""); // Bersihkan EditText untuk mode create
        }

        btnSave.setOnClickListener(v -> {
            String noteText = etNote.getText().toString();

            if(noteText.isEmpty()){
                Toast.makeText(this, "Catatan tidak boleh diisi kosong!", Toast.LENGTH_SHORT).show();
            } else{
                if (existingNote != null) {

                    // Edit mode
                    assert listToDoClass != null;
                    listToDoClass.setNameListToDo(noteText);
                    db.EditListToDo(listToDoClass);
                } else {
                    // Create mode
                    ListToDoClass todolist = new ListToDoClass(noteText);
                    db.AddListToDo(todolist);
                }
            }


            loadData();
            popup.dismiss();
        });
    }


    private void loadData(){
        todolist = db.getAllListToDo();
        listTodoAdapter = new ListTodoAdapter(todolist,this,this);
        rvList.setAdapter(listTodoAdapter);
    }


    @Override
    public void onHapusKegiatan(int position) {
        ListToDoClass selectedHapus = todolist.get(position);
        db.DeleteListToDo(selectedHapus);
        loadData();
    }


    @Override
    public void onUbahKegiatan(int position) {
        ListToDoClass selectedEdit = todolist.get(position);
        String ubahNote = selectedEdit.getNameListToDo();
        showForm(selectedEdit,ubahNote);
        loadData();
    }
}