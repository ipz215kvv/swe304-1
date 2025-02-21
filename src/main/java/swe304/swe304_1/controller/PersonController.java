package swe304.swe304_1.controller;

import swe304.swe304_1.entity.Person;
import swe304.swe304_1.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/persons")
public class PersonController {
    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public String listPersons(Model model) {
        model.addAttribute("persons", service.getAllPersons());
        return "index";
    }

    @PostMapping("/add")
    public String addPerson(@ModelAttribute Person person) {
        service.savePerson(person);
        return "redirect:/persons";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable int id) {
        service.deletePerson(id);
        return "redirect:/persons";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<Person> person = service.getPersonById(id);
        if (person.isPresent()) {
            model.addAttribute("person", person.get());
            return "update-person";
        } else {
            return "redirect:/persons";
        }
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable Integer id, @ModelAttribute Person person) {
        service.updatePerson(id, person);
        return "redirect:/persons";
    }
}
