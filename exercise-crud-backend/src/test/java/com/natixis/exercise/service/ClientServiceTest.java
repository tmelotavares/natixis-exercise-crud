package com.natixis.exercise.service;

import com.natixis.exercise.model.Address;
import com.natixis.exercise.model.Client;
import com.natixis.exercise.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    private ClientService clientService;

    @BeforeEach
    void setUp() {
        ClientRepository clientRepository = mock(ClientRepository.class);
        clientService = new ClientService(clientRepository);
    }

    @Test
    void shouldThrowIfClientIsUnderage() {
        Client client = new Client();
        client.setName("Tavares");
        client.setDateOfBirth(LocalDate.now().minusYears(17)); // 17 years old
        client.setFiscalId("123");

        Address address = new Address();
        address.setStreet("1111");
        address.setNumber("2");
        address.setPostalCode("4567");
        address.setCouncil("Council");
        address.setDistrict("District");

        client.setAddresses(List.of(address));

        assertThrows(IllegalArgumentException.class, () -> {
            clientService.save(client);
        });
    }
}
