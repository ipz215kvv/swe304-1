package swe304.swe304_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import swe304.swe304_1.entity.Person;
import swe304.swe304_1.form.PersonForm;
import swe304.swe304_1.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService service;

    @Autowired
    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Persons List");
        model.addAttribute("view", "person/index");
        model.addAttribute("persons", service.getAllPersons());
        return "layout";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("title", "Create Person");
        model.addAttribute("view", "person/create");
        model.addAttribute("type", "create");
        model.addAttribute("personForm", new PersonForm());
        return "layout";
    }

    @PostMapping("/create")
    public String createPerson(@ModelAttribute("personForm") PersonForm personForm) throws IOException {
        service.savePerson(personForm);
        return "redirect:/person";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        Person person = service.getPersonById(id);
        PersonForm personForm = new PersonForm();

        personForm.setId(person.getId());
        personForm.setName(person.getName());
        personForm.setAddress(person.getAddress());

        model.addAttribute("title", "Update Person");
        model.addAttribute("view", "person/update");
        model.addAttribute("type", "update");
        model.addAttribute("personForm", personForm);
        return "layout";
    }

    @PostMapping("/update")
    public String updatePerson(@ModelAttribute("personForm") PersonForm personForm) throws IOException {
        service.updatePerson(personForm);
        return "redirect:/person";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable Integer id) {
        service.deletePerson(id);
        return "redirect:/person";
    }
}
