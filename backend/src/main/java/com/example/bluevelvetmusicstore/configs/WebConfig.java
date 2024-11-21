package com.example.bluevelvetmusicstore.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

/**
 * Configures consistent serialization of paginated API responses using DTOs.
 *
 * <p>This ensures a stable JSON structure for paginated data, separates internal pagination logic
 * from API output, and allows customization of exposed data.
 */
@Configuration
@EnableSpringDataWebSupport(
    pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class WebConfig {}
