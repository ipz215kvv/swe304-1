package swe304.swe304_1.service;

import swe304.swe304_1.entity.Person;
import swe304.swe304_1.entity.Building;
import swe304.swe304_1.form.PersonForm;
import swe304.swe304_1.repository.PersonRepository;
import swe304.swe304_1.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository repository;
    private final BuildingRepository buildingRepository;
    private final StorageService storageService;

    public PersonService(PersonRepository repository, BuildingRepository buildingRepository, StorageService storageService) {
        this.repository = repository;
        this.buildingRepository = buildingRepository;
        this.storageService = storageService;
    }

    public List<Person> getAllPersons() {
        return repository.findAll();
    }

    public Person getPersonById(Integer id) { return repository.findById(id).orElseThrow(() -> new Error("Person error")); }

    public void savePerson(PersonForm personForm) throws IOException {
        Person person = new Person();
        person.setName(personForm.getName());
        person.setOccupation(personForm.getOccupation());
        person.setFloor(personForm.getFloor());
        person.setNumber(personForm.getNumber());

        if (personForm.getBuildingId() != null) {
            Building building = buildingRepository.findById(personForm.getBuildingId()).orElseThrow(() -> new RuntimeException("Building not found"));
            person.setBuilding(building);
        }

        if (personForm.getImage() != null && !personForm.getImage().isEmpty()) {
            String imageUrl = storageService.storeFile(personForm.getImage());
            person.setImgUrl(imageUrl);
        }

        repository.save(person);
    }

    public void updatePerson(PersonForm updatedPerson) throws IOException {
        Integer id = updatedPerson.getId();
        Optional<Person> existingPerson = repository.findById(id);

        if (existingPerson.isPresent()) {
            Person person = existingPerson.get();
            person.setName(updatedPerson.getName());
            person.setOccupation(updatedPerson.getOccupation());
            person.setFloor(updatedPerson.getFloor());
            person.setNumber(updatedPerson.getNumber());

            if (updatedPerson.getBuildingId() != null) {
                Building building = buildingRepository.findById(updatedPerson.getBuildingId()).orElse(null);
                person.setBuilding(building);
            }

            if (updatedPerson.getImage() != null && !updatedPerson.getImage().isEmpty()) {
                String imageUrl = storageService.storeFile(updatedPerson.getImage());
                person.setImgUrl(imageUrl);
            }

            repository.save(person);
        } else {
            throw new RuntimeException("Person not found with ID: " + updatedPerson);
        }
    }

    public void deletePerson(int id) {
        repository.deleteById(id);
    }
}
