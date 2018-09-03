package com.rangga.tokokita.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Document(collection="categories")
public class Category {
    @Id
    private String _id;
    @NotNull
    private String name;
    private List<Category> child = new ArrayList<>();

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public void setChild(List<Category> child) {
        this.child = child;
    }

    public List<Category> getChild() {
        return child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
