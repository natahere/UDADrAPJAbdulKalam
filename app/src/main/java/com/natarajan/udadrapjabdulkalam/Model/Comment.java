package com.natarajan.udadrapjabdulkalam.Model;

/**
 * Created by Natarajan on 10/9/2016.
 */
public class Comment {
    private int id;
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment;
    }
}