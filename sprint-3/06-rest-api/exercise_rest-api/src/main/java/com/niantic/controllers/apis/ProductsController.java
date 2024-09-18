package com.niantic.controllers.apis;

import com.niantic.models.Product;
import com.niantic.services.MySqlProductDao;
import com.niantic.services.ProductDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController
{
    private ProductDao productDao = new MySqlProductDao();

    @GetMapping("/api/products")
    public List<Product> getProductsByCategory(@RequestParam(required = false) int catId)
    {
        return productDao.getProductsByCategory(catId);
    }

    @GetMapping("/api/products/{id}")
    public Product getProductById(@PathVariable int productId)
    {
        return productDao.getProductById(productId);
    }
}
