package com.niantic.models;

import java.util.ArrayList;

public class Category
{
    private int categoryId;
    private String name;
    private String description;

    private ArrayList<SubCategory> subCategories;

    public Category() {}

    public Category(int categoryId, String name, String description)
    {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    public int getCategoryId() {return categoryId;}

    public void setCategoryId(int categoryId) {this.categoryId = categoryId;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    @Override
    public String toString() {
        return String.format("%-5d %-30s %-60s", categoryId, name, description);
    }
}
