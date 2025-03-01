package swe304.swe304_1.service;

import swe304.swe304_1.entity.Building;
import swe304.swe304_1.form.BuildingForm;
import swe304.swe304_1.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {
    private final BuildingRepository repository;

    public BuildingService(BuildingRepository repository) {
        this.repository = repository;
    }

    public List<Building> getAllBuildings() {
        return repository.findAll();
    }

    public Building getBuildingById(Integer id) { return repository.findById(id).orElseThrow(() -> new Error("Building error")); }

    public void saveBuilding(BuildingForm buildingForm) throws IOException {
        Building building = new Building();
        building.setCountry(buildingForm.getCountry());
        building.setCity(buildingForm.getCity());
        building.setStreet(buildingForm.getStreet());
        building.setNumber(buildingForm.getNumber());

        repository.save(building);
    }

    public void updateBuilding(BuildingForm updatedBuilding) throws IOException {
        Integer id = updatedBuilding.getId();
        Optional<Building> existingBuilding = repository.findById(id);

        if (existingBuilding.isPresent()) {
            Building building = existingBuilding.get();
            building.setCountry(updatedBuilding.getCountry());
            building.setCity(updatedBuilding.getCity());
            building.setStreet(updatedBuilding.getStreet());
            building.setNumber(updatedBuilding.getNumber());

            repository.save(building);
        } else {
            throw new RuntimeException("Building not found with ID: " + updatedBuilding);
        }
    }

    public void deleteBuilding(int id) {
        repository.deleteById(id);
    }
}
