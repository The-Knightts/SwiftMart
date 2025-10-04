package com.swiftmart.swiftmart_backend.service;
// ... existing code ...
import com.opencsv.CSVReader;
import java.io.FileReader;


// ... existing code ...

import com.swiftmart.swiftmart_backend.model.Product;
import com.swiftmart.swiftmart_backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product addProduct(Product p) {
        return repo.save(p);
    }

    public Product updateProduct(Long id, Product p) {
        Product existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        existing.setName(p.getName());
        existing.setQuantity(p.getQuantity());
        existing.setPrice(p.getPrice());
        return repo.save(existing);
    }
    public void importProductsFromCsv(String csvFilePath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                Product product = new Product();
                product.setName(line[0]);
                product.setDescription(line[1]);
                product.setPrice(Double.parseDouble(line[2]));
                product.setQuantity(Integer.parseInt(line[3]));
                // Set other fields as needed
                repo.save(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }
}
