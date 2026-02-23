package com.example.resourcesharing.service;

import com.example.resourcesharing.model.Resource;
import com.example.resourcesharing.model.User;
import com.example.resourcesharing.repository.UserRepository;
import com.example.resourcesharing.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Resource> getAvailableResources() {
        return resourceRepository.findByAvailabilityStatus("AVAILABLE");
    }

    public Resource addResource(String title, String description, String category, Long ownerId) {

        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Resource resource = new Resource();
        resource.setTitle(title);
        resource.setDescription(description);
        resource.setCategory(category);
        resource.setOwner(owner);
        resource.setAvailabilityStatus("AVAILABLE");

        return resourceRepository.save(resource);
    }

    public List<Resource> getResourcesByOwner(Long ownerId) {
        return resourceRepository.findByOwner_Id(ownerId);
    }


}
