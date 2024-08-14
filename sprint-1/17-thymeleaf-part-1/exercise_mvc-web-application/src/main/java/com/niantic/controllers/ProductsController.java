package com.niantic.controllers;

import com.niantic.models.Category;
import com.niantic.models.Product;
import com.niantic.services.CategoryDao;
import com.niantic.services.ProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class ProductsController
{
    ProductDao productDao = new ProductDao();
    CategoryDao categoryDao = new CategoryDao();

    @GetMapping("/products?catId={id}")
    public String getAllProductsByCategory(Model model, @PathVariable int id) {

        ArrayList<Product> products = productDao.getProductsByCategory(id);
        model.addAttribute("products", products);

        Category category = categoryDao.getCategoryById(id);
        model.addAttribute("category", category);

        return "products/index";
    }
}
