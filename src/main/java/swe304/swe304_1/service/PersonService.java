package swe304.swe304_1.service;

import swe304.swe304_1.entity.Person;
import swe304.swe304_1.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> getAllPersons() {
        return repository.findAll();
    }

    public Optional<Person> getPersonById(Integer id) {
        return repository.findById(id);
    }

    public void savePerson(Person person) {
        repository.save(person);
    }

    public void deletePerson(int id) {
        repository.deleteById(id);
    }

    public Person updatePerson(Integer id, Person updatedPerson) {
        Optional<Person> existingPerson = repository.findById(id);

        if (existingPerson.isPresent()) {
            Person person = existingPerson.get();
            person.setName(updatedPerson.getName());
            person.setAddress(updatedPerson.getAddress());
            return repository.save(person);
        } else {
            throw new RuntimeException("Person not found with ID: " + id);
        }
    }
}
