package com.swiftmart.swiftmart_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // allow everything for now (dev)
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Root -> index
        registry.addViewController("/").setViewName("forward:/index.html");

        // Shopkeeper pages
        registry.addViewController("/shopkeeper").setViewName("forward:/shopkeeper-dashboard.html");
        registry.addViewController("/shopkeeper/dashboard").setViewName("forward:/shopkeeper-dashboard.html");
        registry.addViewController("/shopkeeper/inventory").setViewName("forward:/shopkeeper-inventory.html");
        registry.addViewController("/shopkeeper/sales-history").setViewName("forward:/shopkeeper-sales-history.html");
        registry.addViewController("/shopkeeper/vendor-requests").setViewName("forward:/shopkeeper-vendor-request.html");

        // Vendor pages
        registry.addViewController("/vendor").setViewName("forward:/vendor-dashboard.html");
        registry.addViewController("/vendor/dashboard").setViewName("forward:/vendor-dashboard.html");
        registry.addViewController("/vendor/requests").setViewName("forward:/vendor-requests.html");
        registry.addViewController("/vendor/emergency").setViewName("forward:/vendor-emergency.html");
        registry.addViewController("/vendor/shop").setViewName("forward:/vendor-shop.html");
    }
}
