package de.dlh.lhind.exercise.roommgmt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import de.dlh.lhind.exercise.roommgmt.model.Building;
import de.dlh.lhind.exercise.roommgmt.model.Room;
import de.dlh.lhind.exercise.roommgmt.service.RoomService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RoomController.class)
class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService mockedRoomService;

    private static final Long ROOM_ID = 1L;
    public static final String ROOM_NUMBER = "001";
    public static final String MODIFIED_ROOM_NUMBER = "002";
    public static final Integer ROOM_SEATS = 10;
    public static final Boolean PROJECTOR_PRESENT = true;
    public static final String ROOM_URL = "/rooms/";

    private static final Long BUILDING_ID = 1L;
    public static final String BUILDING_NUMBER = "001";
    public static final String BUILDING_DESCRIPTION_STRING = "Zentrale";
    public static final Boolean PUBLIC_ACCESS = true;

    @Test
    @DisplayName("Get All Rooms")
    void shouldReturnAllRooms_whenGetAllRooms_givenValidGetRequest() throws Exception {
        // given
        var room = createRoom();
        List<Room> rooms = Collections.singletonList(room);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        // when
        when(mockedRoomService.getAllRooms()).thenReturn(rooms);

        // then
        mockMvc.perform(get(ROOM_URL))
                .andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(rooms)));
    }

    @Test
    @DisplayName("Add Room")
    void shouldCreateRoom_whenAddRoom_givenValidPostRequest() throws Exception {
        // given
        var room = createRoom();

        // when
        when(mockedRoomService.addRoom(any())).thenReturn(room);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        // then
        mockMvc.perform(post(ROOM_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(room))).andExpect(status().isCreated())
                .andExpect(content().json(objectWriter.writeValueAsString(room)));
    }

    @Test
    @DisplayName("Update Room")
    void shouldUpdateRoom_whenUpdateRoom_givenValidPutRequest() throws Exception {
        // given
        var room = createRoom();
        room.setRoomNumber(MODIFIED_ROOM_NUMBER);

        // when
        when(mockedRoomService.updateRoom(any())).thenReturn(room);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        // then
        mockMvc.perform(put(ROOM_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(room))).andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(room)));
    }

    @Test
    @DisplayName("Delete Room")
    void shouldUDeleteRoom_whenDeleteRoom_givenValidRequest() throws Exception {
        //when
        doNothing().when(mockedRoomService).deleteRoomById(ROOM_ID);

        // then
        mockMvc.perform(delete(ROOM_URL+ROOM_ID)).andExpect(status().isOk());
    }

    private Room createRoom() {
        Room room = new Room();
        Building building = new Building();

        building.setId(BUILDING_ID);
        building.setBuildingNumber(BUILDING_NUMBER);
        building.setDescription(BUILDING_DESCRIPTION_STRING);
        building.setPublicAccess(PUBLIC_ACCESS);

        room.setId(ROOM_ID);
        room.setBuilding(building);
        room.setRoomNumber(ROOM_NUMBER);
        room.setSeats(ROOM_SEATS);
        room.setProjectorPresent(PROJECTOR_PRESENT);

        return room;
    }

}