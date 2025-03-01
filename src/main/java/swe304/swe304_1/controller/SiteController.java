package swe304.swe304_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import swe304.swe304_1.repository.BuildingRepository;
import swe304.swe304_1.repository.PersonRepository;

import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
public class SiteController {
    private final BuildingRepository buildingRepository;
    private final PersonRepository personRepository;

    public SiteController(BuildingRepository buildingRepository, PersonRepository personRepository) {
        this.buildingRepository = buildingRepository;
        this.personRepository = personRepository;
    }

    @GetMapping("")
    public String index(Model model) {
        long buildingCount = buildingRepository.count();
        long personCount = personRepository.count();

        Set<String> uniqueCountries = buildingRepository.findAll().stream()
                .map(building -> building.getCountry().toLowerCase()) // Normalize to lowercase
                .collect(Collectors.toSet());

        Set<String> uniqueOccupations = personRepository.findAll().stream()
                .map(person -> person.getOccupation().toLowerCase()) // Normalize to lowercase
                .collect(Collectors.toSet());

        model.addAttribute("title", "SWE 304");
        model.addAttribute("view", "site/index");
        model.addAttribute("buildingCount", buildingCount);
        model.addAttribute("personCount", personCount);
        model.addAttribute("uniqueCountryCount", uniqueCountries.size());
        model.addAttribute("uniqueOccupationCount", uniqueOccupations.size());

        return "layout";
    }

    @GetMapping("/create")
    public String create(Model model) {
        return "person/create";
    }

}
