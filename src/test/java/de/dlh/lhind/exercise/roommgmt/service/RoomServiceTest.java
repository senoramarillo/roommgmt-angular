package de.dlh.lhind.exercise.roommgmt.service;

import de.dlh.lhind.exercise.roommgmt.repository.RoomRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RoomServiceTest {

    @Mock
    private RoomRepository mockedRoomRepository;
    private RoomService roomService;

    @BeforeEach
    public void setUp() {
        roomService = new RoomService(mockedRoomRepository);
    }

    @Test(expected=NullPointerException.class)
    @DisplayName("Get all rooms by giving null request")
    public void shouldCallRepository_whenGetAllRooms_givenNullObject() {
        // when
        when(mockedRoomRepository.findAll()).thenReturn(null);
        var result = roomService.getAllRooms();

        // then
        assertThat(result).isNull();
        verify(mockedRoomRepository).findAll();
    }

}