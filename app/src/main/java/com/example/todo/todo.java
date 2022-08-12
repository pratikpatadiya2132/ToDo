package com.example.todo;

public class todo {

    String desctitle;
    String desctodo;
    String descdate;
    String editsave;

    public todo() {

    }

    public todo(String desctitle, String desctodo, String descdate, String editsave) {
        this.desctitle = desctitle;
        this.desctodo = desctodo;
        this.descdate = descdate;
        this.editsave = editsave;
    }

    public String getEditsave() {
        return editsave;
    }

    public void setEditsave(String editsave) {
        this.editsave = editsave;
    }

    public String getDesctitle() {
        return desctitle;
    }

    public void setDesctitle(String desctitle) {
        this.desctitle = desctitle;
    }

    public String getDesctodo() {
        return desctodo;
    }

    public void setDesctodo(String desctodo) {
        this.desctodo = desctodo;
    }

    public String getDescdate() {
        return descdate;
    }

    public void setDescdate(String descdate) {
        this.descdate = descdate;
    }
}
