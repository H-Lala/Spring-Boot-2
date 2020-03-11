package com.example.demo2.dao;

import com.example.demo2.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> db = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        db.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public Optional<Person> selectById(UUID id) {
        return db.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> person = selectById(id);
        if (person.isEmpty()) {
            return 0;
        }
        db.remove(person.get());
        return 1;
    }

    @Override
    public int updatePerson(UUID id, Person person) {
        return selectById(id)
                .map(person1 -> {
                    int indexOfUpdate = db.indexOf(person1);
                    if (indexOfUpdate >= 0) {
                        db.set(indexOfUpdate, new Person(id,person.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public List<Person> getAll() {
        return db;
    }
}
