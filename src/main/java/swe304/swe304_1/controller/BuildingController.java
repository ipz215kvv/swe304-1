package swe304.swe304_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import swe304.swe304_1.entity.Building;
import swe304.swe304_1.form.BuildingForm;
import swe304.swe304_1.service.BuildingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
@RequestMapping("/building")
public class BuildingController {

    private final BuildingService service;

    @Autowired
    public BuildingController(BuildingService service) {
        this.service = service;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("title", "Buildings List");
        model.addAttribute("view", "building/index");
        model.addAttribute("buildings", service.getAllBuildings());
        return "layout";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("title", "Create Building");
        model.addAttribute("view", "building/create");
        model.addAttribute("type", "create");
        model.addAttribute("buildingForm", new BuildingForm());
        return "layout";
    }

    @PostMapping("/create")
    public String createBuilding(@ModelAttribute("buildingForm") BuildingForm buildingForm) throws IOException {
        service.saveBuilding(buildingForm);
        return "redirect:/building";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model) {
        Building building = service.getBuildingById(id);
        BuildingForm buildingForm = new BuildingForm();

        buildingForm.setId(building.getId());
        buildingForm.setAddress(building.getAddress());

        model.addAttribute("title", "Update Building");
        model.addAttribute("view", "building/update");
        model.addAttribute("type", "update");
        model.addAttribute("buildingForm", buildingForm);
        return "layout";
    }

    @PostMapping("/update")
    public String updateBuilding(@ModelAttribute("buildingForm") BuildingForm buildingForm) throws IOException {
        service.updateBuilding(buildingForm);
        return "redirect:/building";
    }

    @GetMapping("/delete/{id}")
    public String deleteBuilding(@PathVariable Integer id) {
        service.deleteBuilding(id);
        return "redirect:/building";
    }
}
