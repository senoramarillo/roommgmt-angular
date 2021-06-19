package de.dlh.lhind.exercise.roommgmt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import de.dlh.lhind.exercise.roommgmt.model.Building;
import de.dlh.lhind.exercise.roommgmt.service.BuildingService;
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

@WebMvcTest(controllers = BuildingController.class)
class BuildingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuildingService mockedBuildingService;

    private static final Long BUILDING_ID = 1L;
    public static final String BUILDING_NUMBER = "001";
    public static final String MODIFIED_BUILDING_NUMBER = "002";
    public static final String BUILDING_DESCRIPTION_STRING = "Zentrale";
    public static final Boolean PUBLIC_ACCESS = true;
    public static final String BUILDING_URL = "/buildings/";

    @Test
    @DisplayName("Get All Buildings")
    void shouldReturnAllBuildings_whenGetAllBuildings_givenValidGetRequest() throws Exception {
        // given
        var building = createBuilding();
        List<Building> buildings = Collections.singletonList(building);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        // when
        when(mockedBuildingService.getAllBuildings()).thenReturn(buildings);

        // then
        mockMvc.perform(get(BUILDING_URL))
                .andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(buildings)));
    }

    @Test
    @DisplayName("Add Buildings")
    void shouldCreateBuilding_whenAddBuilding_givenValidPostRequest() throws Exception {
        // given
        var building = createBuilding();

        // when
        when(mockedBuildingService.addBuilding(any())).thenReturn(building);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        // then
        mockMvc.perform(post(BUILDING_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(building))).andExpect(status().isCreated())
                .andExpect(content().json(objectWriter.writeValueAsString(building)));
    }

    @Test
    @DisplayName("Update Buildings")
    void shouldUpdateBuilding_whenUpdateBuilding_givenValidPutRequest() throws Exception {
        // given
        var building = createBuilding();
        building.setBuildingNumber(MODIFIED_BUILDING_NUMBER);

        // when
        when(mockedBuildingService.updateBuilding(any())).thenReturn(building);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

        // then
        mockMvc.perform(put(BUILDING_URL).contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(building))).andExpect(status().isOk())
                .andExpect(content().json(objectWriter.writeValueAsString(building)));
    }

    @Test
    @DisplayName("Delete Buildings")
    void shouldUDeleteBuilding_whenDeleteBuilding_givenValidRequest() throws Exception {
        //when
        doNothing().when(mockedBuildingService).deleteBuildingById(BUILDING_ID);

        // then
        mockMvc.perform(delete(BUILDING_URL+BUILDING_ID)).andExpect(status().isOk());
    }

    private Building createBuilding() {
        Building building = new Building();

        building.setId(BUILDING_ID);
        building.setBuildingNumber(BUILDING_NUMBER);
        building.setDescription(BUILDING_DESCRIPTION_STRING);
        building.setPublicAccess(PUBLIC_ACCESS);

        return building;
    }

}






