package com.example.mysqljpa;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {
    private final PersonRepository personRepository;

    @PostMapping("/users/save")
    public void personSave(@RequestBody Person person) {
        personRepository.save(person);
    }
}
