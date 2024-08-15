package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.models.Product;
import com.niantic.services.CategoryDao;
import com.niantic.services.ProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ProductsController
{
    ProductDao productDao = new ProductDao();
    CategoryDao categoryDao = new CategoryDao();

    @GetMapping("/products")
    public String getAllProductsByCategory(Model model, @RequestParam(required = false) int catId) {

        Category category = categoryDao.getCategoryById(catId);
        ArrayList<Product> products = productDao.getProductsByCategory(catId);

        model.addAttribute("category", category);
        model.addAttribute("products", products);
        model.addAttribute("message", String.format("Products: %s", category.getCategoryName()));

        return "products/index";
    }

    @GetMapping("/products/{id}")
    public String productDetails(Model model, @PathVariable int id)
    {
        Product product = productDao.getProduct(id);
        Category category = categoryDao.getCategoryById(product.getCategoryId());
        String catName = category.getCategoryName();

        model.addAttribute("product", product);
        model.addAttribute("category", category);
        model.addAttribute("categoryName", catName);

        return "products/details";
    }

    @GetMapping("products/add")
    public String addProduct(Model model)
    {
        ArrayList<Category> categories = categoryDao.getCategories();

        model.addAttribute("product", new Product());
        model.addAttribute("categories", categories);
        model.addAttribute("action", "add");

        return "products/add_edit";
    }
}
