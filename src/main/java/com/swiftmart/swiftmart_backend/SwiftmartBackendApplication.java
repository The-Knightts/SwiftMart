package com.swiftmart.swiftmart_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.swiftmart.swiftmart_backend.service.ExcelImportService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class SwiftmartBackendApplication implements CommandLineRunner {

    @Autowired
    private ExcelImportService excelImportService;

    public static void main(String[] args) {
        SpringApplication.run(SwiftmartBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Import products from Excel file on startup (if DB is empty)
        excelImportService.importProductsFromResource("products.xlsx");
    }
}
