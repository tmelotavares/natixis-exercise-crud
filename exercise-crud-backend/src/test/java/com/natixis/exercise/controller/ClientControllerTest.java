package com.natixis.exercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.natixis.exercise.model.Address;
import com.natixis.exercise.model.Client;
import com.natixis.exercise.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClientControllerTest {

    private ClientService clientService;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private Client client;

    @BeforeEach
    void setup() {
        clientService = mock(ClientService.class);
        ClientController controller = new ClientController(clientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        objectMapper.findAndRegisterModules();

        client = new Client();
        client.setId(1L);
        client.setName("Test Client");
        client.setFiscalId("999999999");
        client.setDateOfBirth(LocalDate.of(1990, 1, 1));

        Address address = new Address();
        address.setStreet("A Street");
        address.setNumber("10");
        address.setPostalCode("12345");
        address.setCouncil("Council");
        address.setDistrict("District");

        client.setAddresses(List.of(address));
    }


    @Test
    void testCreateClient() throws Exception {
        when(clientService.save(any())).thenReturn(client);

        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Client"));
    }

    @Test
    void testGetClientById() throws Exception {
        when(clientService.findById(1L)).thenReturn(Optional.of(client));

        mockMvc.perform(get("/api/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fiscalId").value("999999999"));
    }

    @Test
    void testGetClientNotFound() throws Exception {
        when(clientService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/clients/1"))
                .andExpect(status().isNotFound());
    }
}
