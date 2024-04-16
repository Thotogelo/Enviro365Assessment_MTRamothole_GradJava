package com.enviro.assessment.grad001.mtramothole;

import com.enviro.assessment.grad001.mtramothole.model.Waste;
import com.enviro.assessment.grad001.mtramothole.service.WasteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class WasteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WasteService wasteService;

    private Waste waste;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        waste = new Waste();
        waste.setId(1L);
        waste.setWastecategory("test");
        waste.setDisposalguideline("test");
    }

    // Test to check if all wastes are returned
    @Test
    void shouldReturnAllWastes() throws Exception {
        when(wasteService.findAll()).thenReturn(List.of(waste));

        // Perform a GET request to the /v1/api/waste/all endpoint
        mockMvc.perform(get("/v1/api/waste/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(waste))));
    }

    // Test to check if a waste is saved
    @Test
    void shouldSaveWaste() throws Exception {
        when(wasteService.saveWaste(Mockito.any(Waste.class))).thenReturn(waste);

        // Perform a POST request to the /v1/api/waste/save endpoint and check if the response
        // is as expected
        // and use the object mapper to convert the waste object to a JSON string
        mockMvc.perform(post("/v1/api/waste/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(waste)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(waste)));
    }

    // Test to check if a waste is returned by its id
    @Test
    void shouldReturnWasteById() throws Exception {
        when(wasteService.findWasteById(Mockito.anyLong())).thenReturn(waste);

        // Perform a GET request to the /v1/api/waste/1 endpoint and check if the
        // response is as expected
        // and use the object mapper to convert the waste object to a JSON string
        mockMvc.perform(get("/v1/api/waste/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(waste)));
    }

    // Test to check if a waste is returned by its category
    @Test
    void shouldReturnWastesByCategory() throws Exception {
        List<Waste> wasteList = List.of(waste);
        when(wasteService.findWasteByWastecategory(Mockito.anyString())).thenReturn(wasteList);

        // Perform a GET request to the /v1/api/waste/category/test endpoint
        // and check if the response is as expected
        // and use the object mapper to convert the waste object to a JSON string
        mockMvc.perform(get("/v1/api/waste/category/test"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(wasteList)));
    }

    // Test to check if a waste is deleted by its id
    @Test
    void shouldDeleteWasteById() throws Exception {
        Long id = 1L;

        // Mock the removeWasteById method to do nothing, since we are not testing the method itself but the controller
        doNothing().when(wasteService).removeWasteById(id);

        // Perform a DELETE request to the /v1/api/waste/delete/1 endpoint and check if
        // the response is as expected
        mockMvc.perform(delete("/v1/api/waste/delete/" + id))
                .andExpect(status().isOk());

        verify(wasteService, times(1)).removeWasteById(id);
    }

    // Test to check if wastes are deleted by their category
    @Test
    void shouldDeleteWastesByCategory() throws Exception {
        String category = "test";

        // Mock the removeWasteByWastecategory method to do nothing, since we are not
        // testing the method itself but the controller
        doNothing().when(wasteService).removeWasteByWastecategory(category);

        // Perform a DELETE request to the /v1/api/waste/delete/category/test endpoint
        mockMvc.perform(delete("/v1/api/waste/delete/category/" + category))
                .andExpect(status().isOk());

        verify(wasteService, times(1)).removeWasteByWastecategory(category);
    }

    // Test to check if a waste is updated
    @Test
    void shouldUpdateWaste() throws Exception {
        when(wasteService.updateWaste(Mockito.any(Waste.class))).thenReturn(waste);

        // Perform a PUT request to the /v1/api/waste/update endpoint and check if the
        // response is as expected
        mockMvc.perform(put("/v1/api/waste/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(waste)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(waste)));
    }
}