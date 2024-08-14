package com.niantic.controllers;

import com.niantic.services.ProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsController
{
    ProductDao productDao = new ProductDao();

    @GetMapping("/products")
    public String getAllProducts(Model model) {

        return null;
    }
}
