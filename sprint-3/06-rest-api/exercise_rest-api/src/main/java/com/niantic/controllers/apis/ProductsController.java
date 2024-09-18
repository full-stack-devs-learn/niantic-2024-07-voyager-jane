package com.niantic.controllers.apis;

import com.niantic.models.Product;
import com.niantic.services.MySqlProductDao;
import com.niantic.services.ProductDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/products/{productId}")
    public Product getProductById(@PathVariable int productId)
    {
        return productDao.getProductById(productId);
    }

    @PostMapping("/api/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product)
    {
        return productDao.addProduct(product);
    }

    @PutMapping("/api/products/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable int productId, @RequestBody Product product)
    {
        // overwrites so if you want to only update certain parts,
        // make sure to also add old info for other columns
        productDao.updateProduct(productId, product);
    }
}
