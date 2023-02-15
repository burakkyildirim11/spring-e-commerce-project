package com.springCommerce.commerce.controller;

import com.springCommerce.commerce.model.Advertisement;
import com.springCommerce.commerce.repository.AdvertisementRepository;

import com.springCommerce.commerce.service.AdvertisementService;
import jakarta.annotation.PostConstruct;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/advertisement")
public class AdvertisementController {

    private final AdvertisementRepository repository;
    private final AdvertisementService service;

    public AdvertisementController(AdvertisementRepository repository, AdvertisementService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostConstruct
    public void init() {
        Advertisement advertisement = new Advertisement();
        advertisement.setId("K0001");
        advertisement.setTitle("car");
        advertisement.setDescription("secondhand car");
        advertisement.setPrice(100.000);
        advertisement.setCreationTime(Date.from(Instant.now()));
        advertisement.setLastModifiedDate(Date.from(Instant.now()));
        repository.save(advertisement);

    }

    @GetMapping("/{search}")
    public ResponseEntity<List<Advertisement>> getAdvertisement(@PathVariable String search) {
        List<Advertisement> advertisementList = service.getByTitle(search);
        return ResponseEntity.ok(advertisementList);
    }
}