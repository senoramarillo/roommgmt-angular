package de.dlh.lhind.exercise.roommgmt.service;

import de.dlh.lhind.exercise.roommgmt.repository.BuildingRepository;
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
public class BuildingServiceTest {

    @Mock
    private BuildingRepository mockedBuildingRepository;

    @InjectMocks
    private BuildingService buildingService;

    @BeforeEach
    public void setUp() {
        buildingService = new BuildingService(mockedBuildingRepository);
    }

    @Test(expected=NullPointerException.class)
    @DisplayName("Get all buildings by giving null request")
    public void shouldCallRepository_whenGetAllBuildings_givenNullRequest() {
        //when
        when(mockedBuildingRepository.findAll()).thenReturn(null);

        // Execute the service call
        var result = buildingService.getAllBuildings();

        // Assert the response
        assertThat(result).isNull();
        verify(mockedBuildingRepository).findAll();
    }

}