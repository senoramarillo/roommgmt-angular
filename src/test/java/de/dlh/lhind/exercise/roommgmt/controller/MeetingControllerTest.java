package de.dlh.lhind.exercise.roommgmt.controller;

import de.dlh.lhind.exercise.roommgmt.service.MeetingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MeetingController.class)
class MeetingControllerTest {

    private static final Long MEETING_ID = 1L;
    public static final String MEETING_URL = "/meetings/";
    private static final Long ROOM_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeetingService mockedMeetingService;

    @Test
    @DisplayName("Delete Meeting")
    void shouldUDeleteMeeting_whenDeleteMeeting_givenValidRequest() throws Exception {
        //when
        doNothing().when(mockedMeetingService).deleteMeetingById(ROOM_ID);

        // then
        mockMvc.perform(delete(MEETING_URL+MEETING_ID)).andExpect(status().isOk());
    }

}