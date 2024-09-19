package com.niantic.controllers.apis;

import com.niantic.models.Category;
import com.niantic.models.HttpError;
import com.niantic.models.Product;
import com.niantic.services.CategoryDao;
import com.niantic.services.MySqlCategoryDao;
import com.niantic.services.MySqlProductDao;
import com.niantic.services.ProductDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController
{
    private ProductDao productDao = new MySqlProductDao();
    private CategoryDao categoryDao = new MySqlCategoryDao();

    @GetMapping("")
    public ResponseEntity<?> getProductsByCategory(@RequestParam(required = false) int catId)
    {
        // http://localhost:8080/api/products?catId=1

        try
        {
            Category category = categoryDao.getCategory(catId);

            if (category == null)
            {
                var error = new HttpError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Category " + catId + " cannot be found");

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            List<Product> products = productDao.getProductsByCategory(catId);

            return ResponseEntity.ok(products);
        }
        catch (Exception e)
        {
            var error = new HttpError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), "There was an error in getting products from Category " + catId);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("{productId}")
    public ResponseEntity<?> getProductById(@PathVariable int productId)
    {
        // http://localhost:8080/api/products/35

        try
        {
            Product product = productDao.getProductById(productId);

            if (product == null) {
                var error = new HttpError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Product " + productId + " cannot be found");

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            return ResponseEntity.ok(product);
        }
        catch (Exception e)
        {
            var error = new HttpError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), "There was an error in getting Product " + productId);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product)
    {
        return productDao.addProduct(product);
    }

    @PutMapping("{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable int productId, @RequestBody Product product)
    {
        // overwrites so if you want to only update certain parts,
        // make sure to also add old info for other columns
        productDao.updateProduct(productId, product);
    }

    @DeleteMapping("{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int productId)
    {
        productDao.deleteProduct(productId);
    }
}
