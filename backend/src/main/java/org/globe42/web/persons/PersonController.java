package org.globe42.web.persons;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import org.globe42.dao.PersonDao;
import org.globe42.domain.City;
import org.globe42.domain.Person;
import org.globe42.web.exception.NotFoundException;
import org.globe42.web.security.AdminOnly;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for persons
 * @author JB Nizet
 */
@RestController
@RequestMapping(value = "/api/persons")
@Transactional
public class PersonController {

    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping
    public List<PersonDTO> list() {
        return personDao.findAll().stream().map(PersonDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/{personId}")
    public PersonDTO get(@PathVariable("personId") Long id) {
        return personDao.findById(id).map(PersonDTO::new).orElseThrow(() -> new NotFoundException("No person with ID " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO create(@Validated @RequestBody PersonCommandDTO command) {
        Person person = new Person();
        copyCommandToPerson(command, person);

        char mediationCodeLetter = mediationCodeLetter(person);
        person.setMediationCode(mediationCodeLetter + String.valueOf(personDao.nextMediationCode(mediationCodeLetter)));

        return new PersonDTO(personDao.save(person));
    }

    @PutMapping("/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @AdminOnly
    public void update(@PathVariable("personId") Long id, @Validated @RequestBody PersonCommandDTO command) {
        Person person = personDao.findById(id).orElseThrow(() -> new NotFoundException("No person with ID " + id));

        char oldMediationCodeLetter = person.getMediationCode().charAt(0);

        copyCommandToPerson(command, person);

        char newMediationCodeLetter = mediationCodeLetter(person);
        if (newMediationCodeLetter != oldMediationCodeLetter) {
            person.setMediationCode(newMediationCodeLetter + String.valueOf(personDao.nextMediationCode(newMediationCodeLetter)));
        }
    }

    private void copyCommandToPerson(PersonCommandDTO command, Person person) {
        person.setFirstName(command.getFirstName());
        person.setLastName(command.getLastName());
        person.setNickName(command.getNickName());
        person.setBirthDate(command.getBirthDate());
        person.setAddress(command.getAddress());
        if (command.getCity() == null) {
            person.setCity(null);
        }
        else {
            person.setCity(new City(command.getCity().getCode(), command.getCity().getCity()));
        }

        person.setEmail(command.getEmail());
        person.setAdherent(command.isAdherent());
        person.setEntryDate(command.getEntryDate());
        person.setGender(command.getGender());
        person.setPhoneNumber(command.getPhoneNumber());
        person.setMaritalStatus(command.getMaritalStatus());
    }

    private char mediationCodeLetter(Person person) {
        char letter = Character.toUpperCase(person.getLastName().charAt(0));
        if (letter < 'A' || letter > 'Z') {
            letter = 'Z';
        }
        return letter;
    }
}
