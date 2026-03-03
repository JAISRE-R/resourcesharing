package com.example.resourcesharing.controller;

import com.example.resourcesharing.model.Resource;
import com.example.resourcesharing.service.ResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/available")
    public List<Resource> getAvailableResources() {
        return resourceService.getAvailableResources();
    }

    @PostMapping("/add")
    public Resource addResource(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String category,
            @RequestParam Long ownerId

    ) {
        return resourceService.addResource(title, description, category, ownerId);
    }

    @GetMapping("/all")
    public List<Resource> getAllResources() {
        return resourceService.getAllResources();
    }

    @GetMapping("/owner")
    public List<Resource> getResourcesByOwner(@RequestParam Long ownerId) {
        return resourceService.getResourcesByOwner(ownerId);
    }

    @GetMapping("/browse")
    public List<Resource> browseResources(@RequestParam Long userId) {
        return resourceService.getAvailableResourcesExcludingOwner(userId);
    }


}