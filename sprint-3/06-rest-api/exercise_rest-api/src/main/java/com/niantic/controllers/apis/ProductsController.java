package com.niantic.controllers.apis;

import com.niantic.models.Category;
import com.niantic.models.HttpError;
import com.niantic.models.Product;
import com.niantic.services.CategoryDao;
import com.niantic.services.MySqlCategoryDao;
import com.niantic.services.MySqlProductDao;
import com.niantic.services.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/products")
public class ProductsController
{
    private ProductDao productDao;
    private CategoryDao categoryDao = new MySqlCategoryDao();

    @Autowired
    public ProductsController(ProductDao productDao)
    {
        this.productDao = productDao;
    }

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
    public ResponseEntity<?> addProduct(@RequestBody Product product)
    {
        // http://localhost:8080/api/products

        try
        {
            Product newProduct = productDao.addProduct(product);

            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        }
        catch (Exception e)
        {
            var error = new HttpError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), "There was an error adding a new product");

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> updateProduct(@PathVariable int productId, @RequestBody Product product)
    {
        // overwrites so if you want to only update certain parts,
        // make sure to also add old info for other columns
        // http://localhost:8080/api/products/35

        try
        {
            Product checkProduct = productDao.getProductById(productId);

            if (checkProduct == null)
            {
                var error = new HttpError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Product " + productId + " cannot be found");

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            productDao.updateProduct(productId, product);

            return ResponseEntity.noContent().build();
        }
        catch (Exception e)
        {
            var error = new HttpError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), "There was an error editing Product " + productId);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping("{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteProduct(@PathVariable int productId)
    {
        // http://localhost:8080/api/products/35

        try
        {
            Product checkProduct = productDao.getProductById(productId);

            if (checkProduct == null) {
                var error = new HttpError(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(), "Product " + productId + " cannot be found");

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }
            productDao.deleteProduct(productId);

            return ResponseEntity.noContent().build();
        }
        catch (Exception e)
        {
            var error = new HttpError(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), "There was an error deleting Product " + productId);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
