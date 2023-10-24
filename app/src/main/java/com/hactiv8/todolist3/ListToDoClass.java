package com.hactiv8.todolist3;

public class ListToDoClass {
    int idListToDo;
    String nameListToDo;

    public ListToDoClass() {
        super();
    }

    public ListToDoClass(int mIdListToDo, String mNameListToDo){
        super();
        this.idListToDo = mIdListToDo;
        this.nameListToDo = mNameListToDo;
    }

    public ListToDoClass(String mNameListToDo){
        this.nameListToDo = mNameListToDo;
    }

    public int getIdListToDo() {
        return idListToDo;
    }

    public void setIdListToDo(int idListToDo) {
        this.idListToDo = idListToDo;
    }

    public String getNameListToDo() {
        return nameListToDo;
    }

    public void setNameListToDo(String nameListToDo) {
        this.nameListToDo = nameListToDo;
    }
}
