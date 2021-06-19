package de.dlh.lhind.exercise.roommgmt.service;

import de.dlh.lhind.exercise.roommgmt.exception.ResourceNotFoundException;
import de.dlh.lhind.exercise.roommgmt.model.Room;
import de.dlh.lhind.exercise.roommgmt.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoomById(Long id) {
        roomRepository.deleteRoomById(id);
    }

    public Room getRoomById(String roomNumber) {
        return roomRepository.getRoomById(roomNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Room by number " + roomNumber + " was not found"));
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Object> geAllRoomsOfBuilding(String buildingNumber) {
        return roomRepository.geAllRoomsOfBuilding(buildingNumber);
    }

    public Object getBuildingAndRoomById(String buildingNumber, String roomNumber) {
        return roomRepository.getBuildingAndRoomById(buildingNumber, roomNumber)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("Building or Room by buildingNumber " + buildingNumber + " roomNumber: " + roomNumber + " was not found"));
    }

    public List<Room> getPublicRooms() {
        return roomRepository.getPublicRooms();
    }

}
