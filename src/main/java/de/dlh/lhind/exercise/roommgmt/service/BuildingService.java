package de.dlh.lhind.exercise.roommgmt.service;

import de.dlh.lhind.exercise.roommgmt.exception.ResourceNotFoundException;
import de.dlh.lhind.exercise.roommgmt.model.Building;
import de.dlh.lhind.exercise.roommgmt.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BuildingService {

    private final BuildingRepository buildingRepository;

    @Autowired
    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public Building addBuilding(Building building) {
        return buildingRepository.save(building);
    }

    public Building updateBuilding(Building building) {
        return buildingRepository.save(building);
    }

    public void deleteBuildingById(Long id) {
        buildingRepository.deleteBuildingById(id);
    }

    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    public Building getBuildingById(String buildingNumber) {
        return buildingRepository.getBuildingById(buildingNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Building by number " + buildingNumber + " was not found"));
    }

    public List<Building> getPublicBuildings() {
        return buildingRepository.getPublicBuildings();
    }

}
