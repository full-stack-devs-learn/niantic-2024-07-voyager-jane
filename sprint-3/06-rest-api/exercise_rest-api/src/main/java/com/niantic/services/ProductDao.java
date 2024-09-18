package com.niantic.services;

import com.niantic.models.Product;

import java.util.List;

public interface ProductDao
{
    List<Product> getProductsByCategory(int categoryId);

    Product getProductById(int productId);
}
