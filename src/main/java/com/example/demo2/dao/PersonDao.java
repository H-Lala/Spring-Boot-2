package com.example.demo2.dao;

import com.example.demo2.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
    int insertPerson(UUID id, Person person);

    default int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
    public List<Person> getAll();
    Optional<Person> selectById(UUID id);
    int deletePersonById(UUID id);
    int updatePerson(UUID id , Person person);
}
