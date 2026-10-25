package com.example.linkedInProject.ConnectionService.service;


import com.example.linkedInProject.ConnectionService.entity.Person;
import com.example.linkedInProject.ConnectionService.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionsService {
    private final PersonRepository personRepository;

    public List<Person> getFirstDegreeConnectionsOfUser(Long userId)
    {
        log.info("Getting first degree connection of user with id: {}", userId);

        return personRepository.getFirstDegreeConnections(userId);
    }
}
