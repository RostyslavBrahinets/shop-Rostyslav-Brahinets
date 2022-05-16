package com.shop.controllers;

import com.shop.models.Product;
import com.shop.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = ProductController.PRODUCTS_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    public static final String PRODUCTS_URL = "/web-api/products";
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product findByIdProduct(@PathVariable long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public Product savePerson(
        @RequestBody Product product,
        HttpServletResponse response
    ) throws IOException {
        response.sendRedirect("/products");

        if (product.getImage().length == 0) {
            String imagePath = "images/empty.jpg";
            BufferedImage image = ImageIO.read(new File(imagePath));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            product.setImage(bytes);
        }

        return productService.addProduct(product);
    }

    @PostMapping("/{id}")
    public void deleteProduct(
        @PathVariable int id,
        HttpServletResponse response
    ) throws IOException {
        productService.deleteProduct(id);
        response.sendRedirect("/products");
    }
}
