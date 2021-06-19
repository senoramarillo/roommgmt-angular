package de.dlh.lhind.exercise.roommgmt.controller;

import de.dlh.lhind.exercise.roommgmt.model.Room;
import de.dlh.lhind.exercise.roommgmt.service.RoomService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4500"})
public class RoomController {

    private final RoomService roomService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomController.class);

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        LOGGER.info("Add Room: {}", room);
        var newRoom = roomService.addRoom(room);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Room> updateRoom(@RequestBody Room room) {
        LOGGER.info("Update Room: {}", room);
        var updateRoom = roomService.updateRoom(room);
        return new ResponseEntity<>(updateRoom, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Room> deleteRoomById(@PathVariable("id") Long id) {
        LOGGER.info("Delete Room: {}", id);
        roomService.deleteRoomById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieves all rooms",
            notes = "A list of rooms",
            response = Room.class,
            responseContainer = "List",
            produces = "application/json")
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        LOGGER.info("Get All Rooms");
        List<Room> rooms =  roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

    @GetMapping("/{roomNumber}")
    public ResponseEntity<Room> getRoomById(@PathVariable("roomNumber") String roomNumber) {
        LOGGER.info("Get Room by roomNumber: {}", roomNumber);
        var room =  roomService.getRoomById(roomNumber);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @GetMapping("/buildings/{buildingNumber}/rooms")
    public ResponseEntity<List<Object>> geAllRoomsOfBuilding(@PathVariable("buildingNumber") String buildingNumber) {
        LOGGER.info("Get All Rooms of Building");
        var room = roomService.geAllRoomsOfBuilding(buildingNumber);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @GetMapping("/buildings/{buildingNumber}/rooms/{roomNumber}")
    public ResponseEntity<Object> getBuildingAndRoomById(@PathVariable("buildingNumber") String buildingNumber,
                                                       @PathVariable("roomNumber") String roomNumber) {
        LOGGER.info("Get Building by buildingNumber and roomNumber: {}{}", buildingNumber, roomNumber);
        var building = roomService.getBuildingAndRoomById(buildingNumber, roomNumber);
        return new ResponseEntity<>(building, HttpStatus.OK);
    }

    @GetMapping("/public")
    public ResponseEntity<List<Room>> getPublicBuildings() {
        LOGGER.info("Get All Public Rooms");
        List<Room> rooms = roomService.getPublicRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

}
