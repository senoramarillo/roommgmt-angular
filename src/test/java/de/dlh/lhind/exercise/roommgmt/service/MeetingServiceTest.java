package de.dlh.lhind.exercise.roommgmt.service;

import de.dlh.lhind.exercise.roommgmt.repository.MeetingRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MeetingServiceTest {

    @Mock
    private MeetingRepository mockedMeetingRepository;

    @InjectMocks
    private MeetingService meetingService;

    @BeforeEach
    public void setUp() {
        meetingService = new MeetingService(mockedMeetingRepository);
    }

    @Test(expected=NullPointerException.class)
    @DisplayName("Get all meeting by giving null request")
    public void shouldCallRepository_whenGetAllMeetings_givenNullObject() {
        // when
        when(mockedMeetingRepository.findAll()).thenReturn(null);
        var result = meetingService.getAllMeetings();

        // then
        assertThat(result).isNull();
        verify(mockedMeetingRepository).findAll();
    }
}
