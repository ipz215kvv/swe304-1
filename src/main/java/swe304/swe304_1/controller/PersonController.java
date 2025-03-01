package swe304.swe304_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import swe304.swe304_1.entity.Person;
import swe304.swe304_1.entity.Building;
import swe304.swe304_1.repository.BuildingRepository;
import swe304.swe304_1.form.PersonForm;
import swe304.swe304_1.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService service;
    private final BuildingRepository buildingRepository;

    @Autowired
    public PersonController(PersonService service, BuildingRepository buildingRepository) {
        this.service = service;
        this.buildingRepository = buildingRepository;
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
        List<Building> buildings = buildingRepository.findAll();

        model.addAttribute("buildings", buildings);
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
        personForm.setBuildingId(person.getBuilding() != null ? person.getBuilding().getId() : null);
        personForm.setOccupation(person.getOccupation());
        personForm.setFloor(person.getFloor());
        personForm.setNumber(person.getNumber());
        personForm.setImgUrl(person.getImgUrl());

        List<Building> buildings = buildingRepository.findAll();
        model.addAttribute("buildings", buildings);
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
