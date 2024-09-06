package com.niantic.models;
import jakarta.validation.constraints.*;

public class Product
{
    private int productId;
    private int categoryId;

    @Size(min=3, message="Product Name must be at least 3 characters.")
    @NotBlank(message="Product Name is required.")
    private String productName;

    private String quantityPerUnit;

    @NotNull(message="Unit Price cannot be null.")
    @DecimalMin(value = "0.1", inclusive = true, message="Unit Price must be greater than $0.01.")
    private double unitPrice;

    private int unitsInStock;
    private int unitsOnOrder;
    private int reorderLevel;

    public Product(int productId, int categoryId, String productName, String quantityPerUnit, double unitPrice, int unitsInStock, int unitsOnOrder, int reorderLevel)
    {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reorderLevel = reorderLevel;
    }

    public Product()
    {
    }

    public int getProductId()
    {
        return productId;
    }

    public void setProductId(int productId)
    {
        this.productId = productId;
    }

    public int getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(int categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getQuantityPerUnit()
    {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit)
    {
        this.quantityPerUnit = quantityPerUnit;
    }

    public double getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public int getUnitsInStock()
    {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock)
    {
        this.unitsInStock = unitsInStock;
    }

    public int getUnitsOnOrder()
    {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(int unitsOnOrder)
    {
        this.unitsOnOrder = unitsOnOrder;
    }

    public int getReorderLevel()
    {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel)
    {
        this.reorderLevel = reorderLevel;
    }
}
