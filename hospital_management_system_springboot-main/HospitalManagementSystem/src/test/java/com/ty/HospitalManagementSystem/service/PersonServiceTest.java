package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.dao.Addressdao;
import com.ty.HospitalManagementSystem.dao.Persondao;
import com.ty.HospitalManagementSystem.dto.Address;
import com.ty.HospitalManagementSystem.dto.Person;
import com.ty.HospitalManagementSystem.exception.IdNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private Persondao persondao;

    @Mock
    private Addressdao addressdao;

    @InjectMocks
    private PersonService personService;

    private Person person;
    private Address address;

    @BeforeEach
    void setup() {
        address = new Address();
        address.setId(1);
        address.setCity("Bangalore");

        person = new Person();
        person.setId(10);
        person.setName("DK");
        person.setEmail("dk@mail.com");
        person.setPhone(9999999999L);
        person.setAddress(address);
    }

    @Test
    void savePerson_withExistingAddress_shouldSave() {
        when(addressdao.getaddressbyid(1)).thenReturn(Optional.of(address));
        when(persondao.savePerson(person)).thenReturn(person);

        Person saved = personService.savePerson(person);

        assertNotNull(saved);
        verify(addressdao).getaddressbyid(1);
        verify(persondao).savePerson(person);
    }

    @Test
    void savePerson_addressNotFound_shouldThrowException() {
        when(addressdao.getaddressbyid(1)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class,
                () -> personService.savePerson(person));
    }



    @Test
    void updatePerson_success() {
        when(persondao.getpersonbyid(10)).thenReturn(Optional.of(person));
        when(addressdao.getaddressbyid(1)).thenReturn(Optional.of(address));
        when(persondao.savePerson(person)).thenReturn(person);

        Person updated = personService.updatePerson(10, person);

        assertEquals("DK", updated.getName());
        verify(persondao).savePerson(person);
    }

    @Test
    void updatePerson_personNotFound_shouldThrowException() {
        when(persondao.getpersonbyid(10)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class,
                () -> personService.updatePerson(10, person));
    }


    @Test
    void deletePerson_success() {
        when(persondao.getpersonbyid(10)).thenReturn(Optional.of(person));
        when(persondao.deletePerson(person)).thenReturn(person);

        Person deleted = personService.deletePerson(10);

        assertNotNull(deleted);
        verify(persondao).deletePerson(person);
    }

    @Test
    void deletePerson_notFound_shouldThrowException() {
        when(persondao.getpersonbyid(10)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class,
                () -> personService.deletePerson(10));
    }


    @Test
    void getPersonById_success() {
        when(persondao.getpersonbyid(10)).thenReturn(Optional.of(person));

        Person found = personService.getpersonbyid(10);

        assertEquals(10, found.getId());
    }

    @Test
    void getPersonById_notFound_shouldThrowException() {
        when(persondao.getpersonbyid(10)).thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class,
                () -> personService.getpersonbyid(10));
    }



    @Test
    void getAllPersons_success() {
        List<Person> persons = List.of(person);
        Page<Person> page = new PageImpl<>(persons);

        when(persondao.getAllPerson(any(Pageable.class))).thenReturn(page);

        List<Person> result = personService.getAllPersons(0, 5, "asc");

        assertEquals(1, result.size());
    }

    @Test
    void getAllPersons_empty_shouldThrowException() {
        Page<Person> emptyPage = new PageImpl<>(Collections.emptyList());

        when(persondao.getAllPerson(any(Pageable.class))).thenReturn(emptyPage);

        assertThrows(IdNotFoundException.class,
                () -> personService.getAllPersons(0, 5, "asc"));
    }
}