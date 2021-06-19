package de.dlh.lhind.exercise.roommgmt.repository;

import de.dlh.lhind.exercise.roommgmt.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("FROM Room r WHERE r.roomNumber = (:roomNumber)")
    Optional<Room> getRoomById(@Param("roomNumber") String roomNumber);

    @Query("FROM Building b, Room r WHERE b.buildingNumber = (:buildingNumber) AND r.roomNumber = (:roomNumber)")
    Optional<Object> getBuildingAndRoomById(String buildingNumber, String roomNumber);

    @Query(value = "SELECT r FROM Room r JOIN Building b ON b.id = r.building.id WHERE b.buildingNumber = (:buildingNumber)")
    List<Object> geAllRoomsOfBuilding(String buildingNumber);

    @Query("SELECT r FROM Room r JOIN Building b ON b.id = r.building.id WHERE b.publicAccess = true")
    List<Room> getPublicRooms();

    void deleteRoomById(Long id);

}
