package com.shop.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductViewController {
    @GetMapping
    public String index() {
        return "products/index";
    }

    @GetMapping("/{id}")
    public String get(
        @PathVariable long id,
        Model model
    ) {
        model.addAttribute("id", id);
        return "products/get";
    }
}
