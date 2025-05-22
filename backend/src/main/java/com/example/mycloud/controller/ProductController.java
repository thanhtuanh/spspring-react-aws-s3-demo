package com.example.mycloud.controller;

import com.example.mycloud.model.Product;
import com.example.mycloud.service.FileUploadService;
import com.example.mycloud.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService service;
    private final FileUploadService fileUploadService;

    public ProductController(ProductService service, FileUploadService fileUploadService) {
        this.service = service;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping
    public List<Product> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Product upload(@RequestParam("title") String title,
            @RequestParam("file") MultipartFile file) throws IOException {
        return fileUploadService.uploadAndSaveProduct(title, file);
    }

    @GetMapping("/presigned-url/{filename}")
    public String getPresignedUrl(@PathVariable String filename) {
        return fileUploadService.generatePresignedUrl(filename);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/presigned-upload-url/{filename}")
    public String getPresignedUploadUrl(@PathVariable String filename) {
        return fileUploadService.generatePresignedUploadUrl(filename);
    }

}
