package com.example.demo.product;

import com.example.demo.exception.ValidateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Iterable> getAll() {
        Iterable products = productService.getAll();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody ProductModel product) {
        validate(product);
        productService.add(product);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ProductModel product) {
        validate(product);
        productService.update(product);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void validate(ProductModel model) {
        List<String> errors = new ArrayList<>();

        if(model.getPrice() == null) {
            errors.add("price");
        }
        if(model.getName() == null) {
            errors.add("name");
        }
        if(!errors.isEmpty()) {
            throw new ValidateException(String.join(",", errors));
        }
    }
}
