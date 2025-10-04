package com.swiftmart.swiftmart_backend.service;

import com.swiftmart.swiftmart_backend.model.Product;
import com.swiftmart.swiftmart_backend.repository.ProductRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class ExcelImportService {
    private final ProductRepository repo;

    public ExcelImportService(ProductRepository repo) {
        this.repo = repo;
    }

    public void importProductsFromResource(String resourceFilename) {
        // Skip if DB already has data
        if (repo.count() > 0) {
            System.out.println("Products present in DB, skipping Excel import.");
            return;
        }

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(resourceFilename)) {
            if (is == null) {
                System.out.println("Resource not found: " + resourceFilename);
                return;
            }
            try (Workbook workbook = new XSSFWorkbook(is)) {
                Sheet sheet = workbook.getSheetAt(0);
                DataFormatter formatter = new DataFormatter();
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) continue; // header
                    String name = formatter.formatCellValue(row.getCell(0));
                    String qtyStr = formatter.formatCellValue(row.getCell(1));
                    String priceStr = formatter.formatCellValue(row.getCell(2));
                    if (name == null || name.trim().isEmpty()) continue;
                    int qty = 0;
                    double price = 0.0;
                    try { qty = (int) Double.parseDouble(qtyStr); } catch (Exception ignored) {}
                    try { price = Double.parseDouble(priceStr); } catch (Exception ignored) {}
                    Product p = new Product();
                    p.setName(name.trim());
                    p.setQuantity(qty);
                    p.setPrice(price);
                    repo.save(p);
                }
            }
            System.out.println("Excel import finished.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
