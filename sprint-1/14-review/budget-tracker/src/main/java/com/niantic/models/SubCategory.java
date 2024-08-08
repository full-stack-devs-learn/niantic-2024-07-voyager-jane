package com.niantic.models;

public class SubCategory
{
    private int subCategoryId;
    private String subCategoryName;
    private int categoryId;
    private String description;

    public SubCategory() {}

    public SubCategory(int subCategoryId, String subCategoryName, int categoryId, String description)
    {
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.categoryId = categoryId;
        this.description = description;
    }

    public int getSubCategoryId() {return subCategoryId;}

    public void setSubCategoryId(int subCategoryId) {this.subCategoryId = subCategoryId;}

    public String getSubCategoryName() {return subCategoryName;}

    public void setSubCategoryName(String subCategoryName) {this.subCategoryName = subCategoryName;}

    public int getCategoryId() {return categoryId;}

    public void setCategoryId(int categoryId) {this.categoryId = categoryId;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}
}
