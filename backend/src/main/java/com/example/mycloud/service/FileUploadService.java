package com.example.mycloud.service;

import com.example.mycloud.model.Product;
import com.example.mycloud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;

import java.time.Duration;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadService {

    private final S3Client s3Client;
    private final ProductRepository productRepository;
    private final S3Presigner s3Presigner;

    @Value("${aws.s3.bucket}")
    private String bucket;

    public FileUploadService(S3Client s3Client, ProductRepository repo, S3Presigner s3Presigner) {
        this.s3Client = s3Client;
        this.productRepository = repo;
        this.s3Presigner = s3Presigner;
    }

    public String generatePresignedUrl(String filename) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucket)
                .key(filename)
                .build();

        PresignedGetObjectRequest presignedRequest = s3Presigner.presignGetObject(
                builder -> builder.getObjectRequest(getObjectRequest).signatureDuration(Duration.ofMinutes(10)));

        return presignedRequest.url().toString();
    }

    public Product uploadAndSaveProduct(String title, MultipartFile file) throws IOException {
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(filename)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));

        String imageUrl = "https://" + bucket + ".s3.amazonaws.com/" + filename;

        // Bildmetadaten lesen
        BufferedImage image = ImageIO.read(file.getInputStream());
        int width = image != null ? image.getWidth() : 0;
        int height = image != null ? image.getHeight() : 0;

        Product product = new Product();
        product.setTitle(title);
        product.setFilename(filename); // ✅ korrigiert!
        product.setImageUrl(imageUrl);
        product.setFiletype(file.getContentType());
        product.setFilesize(file.getSize());
        product.setWidth(width);
        product.setHeight(height);

        System.out.println("✅ Metadaten:");
        System.out.println("Filename: " + filename);
        System.out.println("Width: " + width + ", Height: " + height);
        System.out.println("Filesize: " + file.getSize());
        System.out.println("Filetype: " + file.getContentType());
        System.out.println("Image URL: " + imageUrl);
        System.out.println("✅ Bild erfolgreich hochgeladen!");
        System.out.println("✅ Produkt erfolgreich gespeichert!");
        return productRepository.save(product);
    }

    public String generatePresignedUploadUrl(String filename) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(filename)
                .contentType("image/jpeg") // Optional: kannst du dynamisch machen
                .build();

        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(
                b -> b.putObjectRequest(putObjectRequest).signatureDuration(Duration.ofMinutes(10)));

        return presignedRequest.url().toString();
    }

}
