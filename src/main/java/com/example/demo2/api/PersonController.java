package com.example.demo2.api;

import com.example.demo2.model.Person;
import com.example.demo2.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NonNull  @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping(path = "{id}")
    public Person selectById(@PathVariable("id") UUID id) {
        return personService.selectById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        personService.deleteById(id);
    }
    @PutMapping(path = "{id}")
    public void update(@PathVariable("id") UUID id,@Valid @NonNull @RequestBody Person person){
        personService.updateById(id, person);
    }
}
