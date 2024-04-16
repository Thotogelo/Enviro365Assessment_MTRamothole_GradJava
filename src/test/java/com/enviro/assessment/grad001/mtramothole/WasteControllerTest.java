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

    @Test
    void shouldReturnAllWastes() throws Exception {
        when(wasteService.findAll()).thenReturn(List.of(waste));

        mockMvc.perform(get("/v1/api/waste/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(waste))));
    }

    @Test
    void shouldSaveWaste() throws Exception {
        when(wasteService.saveWaste(Mockito.any(Waste.class))).thenReturn(waste);

        mockMvc.perform(post("/v1/api/waste/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(waste)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(waste)));
    }

    @Test
    void shouldReturnWasteById() throws Exception {
        when(wasteService.findWasteById(Mockito.anyLong())).thenReturn(waste);

        mockMvc.perform(get("/v1/api/waste/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(waste)));
    }

    @Test
    void shouldReturnWastesByCategory() throws Exception {
        List<Waste> wasteList = List.of(waste);
        when(wasteService.findWasteByWastecategory(Mockito.anyString())).thenReturn(wasteList);

        mockMvc.perform(get("/v1/api/waste/category/test"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(wasteList)));
    }

    @Test
    void shouldDeleteWasteById() throws Exception {
        Long id = 1L;

        Mockito.doNothing().when(wasteService).removeWasteById(id);

        mockMvc.perform(delete("/v1/api/waste/delete/" + id))
                .andExpect(status().isOk());

        Mockito.verify(wasteService, Mockito.times(1)).removeWasteById(id);
    }

    @Test
    void shouldDeleteWastesByCategory() throws Exception {
        String category = "test";

        Mockito.doNothing().when(wasteService).removeWasteByWastecategory(category);

        mockMvc.perform(delete("/v1/api/waste/delete/category/" + category))
                .andExpect(status().isOk());

        Mockito.verify(wasteService, Mockito.times(1)).removeWasteByWastecategory(category);
    }

    @Test
    void shouldUpdateWaste() throws Exception {
        when(wasteService.updateWaste(Mockito.any(Waste.class))).thenReturn(waste);

        mockMvc.perform(put("/v1/api/waste/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(waste)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(waste)));
    }
}