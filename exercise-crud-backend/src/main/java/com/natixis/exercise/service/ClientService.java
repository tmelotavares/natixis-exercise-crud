package com.natixis.exercise.service;

import com.natixis.exercise.model.Client;
import com.natixis.exercise.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public Client save(Client client) {
        validateAge(client.getDateOfBirth());
        return clientRepository.save(client);
    }

    private void validateAge(LocalDate dob) {
        if (dob != null) {
            int age = Period.between(dob, LocalDate.now()).getYears();
            if (age < 18) {
                throw new IllegalArgumentException("Client must have at least 18 years old");
            }
        }
    }


    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
