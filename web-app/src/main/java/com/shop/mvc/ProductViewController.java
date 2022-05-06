package com.shop.mvc;

import com.shop.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductViewController {
    @GetMapping
    public String index() {
        return "products/index";
    }
}
