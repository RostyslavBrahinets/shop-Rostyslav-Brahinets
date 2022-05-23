package com.shop.controllers;

import com.shop.models.Product;
import com.shop.services.ProductService;
import com.shop.utilities.ImageUtility;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = ProductController.PRODUCTS_URL)
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
    public Product savePerson(@RequestBody Product product) throws IOException {
        if (product.getImage().length > 0) {
            return productService.addProduct(product);
        }

        product = productService.addProduct(product);
        Product newProduct = productService.getProduct(product.getBarcode());
        String imagePath = Objects.requireNonNull(getClass().getClassLoader().getResource(
            "static/images/empty.jpg"
        )).getFile();
        byte[] byteImage = ImageUtility.imageToBytes(new File(imagePath));
        productService.addImage(byteImage, newProduct.getId());
        newProduct.setImage(byteImage);
        return newProduct;
    }

    @PostMapping("/{barcode}")
    public String deleteProduct(@PathVariable String barcode) {
        productService.deleteProduct(barcode);
        return "Product Successfully Deleted";
    }

    @GetMapping("/image/{id}")
    public void showProductImage(
        @PathVariable long id,
        HttpServletResponse response
    ) throws IOException {
        Product product = productService.getProduct(id);
        InputStream is = new ByteArrayInputStream(product.getImage());
        IOUtils.copy(is, response.getOutputStream());
    }
}
