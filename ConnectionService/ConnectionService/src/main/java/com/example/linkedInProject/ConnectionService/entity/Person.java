package com.example.linkedInProject.ConnectionService.entity;


// This is not a real user, actual user is in UserService. This is just for connections

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Data
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String name;

}
