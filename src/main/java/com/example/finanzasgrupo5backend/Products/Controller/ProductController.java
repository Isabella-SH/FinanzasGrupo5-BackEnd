package com.example.finanzasgrupo5backend.Products.Controller;

import com.example.finanzasgrupo5backend.Products.Model.ProductRequest;
import com.example.finanzasgrupo5backend.Products.Model.ProductResponse;
import com.example.finanzasgrupo5backend.Products.Service.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Product Controller")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService){
        this.productService=productService;
    }

    @Operation(summary = "Create a Product")
    @PostMapping("/products")
    public ResponseEntity<ProductResponse> createProduct
            (@RequestParam(name = "storeId") Long storeId, @RequestBody ProductRequest productRequest) {

        var res = productService.createProduct(productRequest, storeId);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtain a list of all products")
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        var res = productService.getAllProducts();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Obtain a list of products by store Id")
    @GetMapping("/products/store/{id}")
    public ResponseEntity<List<ProductResponse>> getProductsByStoreId(@PathVariable(name = "id") Long storeId) {
        var res = productService.getProductsByStoreId(storeId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Update a product")
    @PatchMapping("/products/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable(name = "id") Long productId, @RequestParam String name, @RequestParam Double price) {
        long storeId=1;
        var res = productService.updateProduct(productId, name, price, Long.valueOf(storeId));
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(res);
    }
    @Operation(summary = "Delete a product")
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

}
