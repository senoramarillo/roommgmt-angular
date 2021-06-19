package de.dlh.lhind.exercise.roommgmt.controller;

import de.dlh.lhind.exercise.roommgmt.model.Building;
import de.dlh.lhind.exercise.roommgmt.service.BuildingService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = {"/buildings"})
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4500"})
public class BuildingController {

    private final BuildingService buildingService;
    private static final Logger LOGGER = LoggerFactory.getLogger(BuildingController.class);

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @PostMapping
    public ResponseEntity<Building> addBuilding(@Valid @RequestBody Building building) {
        LOGGER.info("Add Building: {}", building);
        var newBuilding = buildingService.addBuilding(building);
        return new ResponseEntity<>(newBuilding, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Building> updateBuilding(@RequestBody Building building) {
        LOGGER.info("Update Building: {}", building);
        var updateBuilding = buildingService.updateBuilding(building);
        return new ResponseEntity<>(updateBuilding, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Building> deleteBuildingById(@PathVariable("id") Long id) {
        LOGGER.info("Delete Building: {}", id);
        buildingService.deleteBuildingById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieves all buildings",
            notes = "A list of buildings",
            response = Building.class,
            responseContainer = "List",
            produces = "application/json")
    @GetMapping
    public ResponseEntity<List<Building>> getAllBuildings() {
        LOGGER.info("Get All Buildings");
        List<Building> buildings = buildingService.getAllBuildings();
        return new ResponseEntity<>(buildings, HttpStatus.OK);
    }

    @GetMapping("/{buildingNumber}")
    public ResponseEntity<Building> getBuildingById(@PathVariable("buildingNumber") String buildingNumber) {
        LOGGER.info("Get Building by buildingNumber: {}", buildingNumber);
        var building = buildingService.getBuildingById(buildingNumber);
        return new ResponseEntity<>(building, HttpStatus.OK);
    }

    @GetMapping("/public")
    public ResponseEntity<List<Building>> getPublicBuildings() {
        LOGGER.info("Get All Public Buildings");
        List<Building> building = buildingService.getPublicBuildings();
        return new ResponseEntity<>(building, HttpStatus.OK);
    }

}
