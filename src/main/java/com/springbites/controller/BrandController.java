package com.springbites.controller;

import com.springbites.dto.BrandRequest;
import com.springbites.dto.BrandResponse;
import com.springbites.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public BrandResponse createBrand(
            @Valid @RequestBody BrandRequest request) {
        return brandService.createBrand(request);
    }

    @GetMapping
    public List<BrandResponse> getAllBrands() {
        return brandService.getAllBrands();
    }
}