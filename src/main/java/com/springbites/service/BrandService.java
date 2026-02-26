package com.springbites.service;

import com.springbites.dto.BrandRequest;
import com.springbites.dto.BrandResponse;
import com.springbites.entity.Brand;
import com.springbites.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandResponse createBrand(BrandRequest request) {

        Brand brand = new Brand();
        brand.setName(request.getName());

        Brand saved = brandRepository.save(brand);

        return mapToResponse(saved);
    }

    public List<BrandResponse> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private BrandResponse mapToResponse(Brand brand) {
        BrandResponse response = new BrandResponse();
        response.setId(brand.getId());
        response.setName(brand.getName());
        return response;
    }
}