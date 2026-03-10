package com.ty.HospitalManagementSystem.service;

import com.ty.HospitalManagementSystem.dao.Addressdao;
import com.ty.HospitalManagementSystem.dto.Address;
import com.ty.HospitalManagementSystem.exception.IdNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private Addressdao addressdao;

    @InjectMocks
    private AddressService addressService;

    private Address address;

    @BeforeEach
    void setup() {
        address = new Address();
        address.setId(1);
        address.setCity("Bangalore");
        address.setState("Karnataka");
        address.setPincode(560001);
    }



    @Test
    void saveAddress_success() {
        when(addressdao.saveAddress(address)).thenReturn(address);

        Address saved = addressService.saveAddress(address);

        assertNotNull(saved);
        assertEquals("Bangalore", saved.getCity());
        verify(addressdao).saveAddress(address);
    }

    @Test
    void updateAddress_success() {
        when(addressdao.updateAddress(1, address))
                .thenReturn(Optional.of(address));

        Address updated = addressService.updateAddress(1, address);

        assertEquals(1, updated.getId());
        verify(addressdao).updateAddress(1, address);
    }

    @Test
    void updateAddress_notFound_shouldThrowException() {
        when(addressdao.updateAddress(1, address))
                .thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class,
                () -> addressService.updateAddress(1, address));
    }



    @Test
    void deleteAddress_success() {
        when(addressdao.deleteAddress(1))
                .thenReturn(Optional.of(address));

        Address deleted = addressService.deleteAddress(1);

        assertNotNull(deleted);
        verify(addressdao).deleteAddress(1);
    }

    @Test
    void deleteAddress_notFound_shouldThrowException() {
        when(addressdao.deleteAddress(1))
                .thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class,
                () -> addressService.deleteAddress(1));
    }

    @Test
    void getAddressById_success() {
        when(addressdao.getaddressbyid(1))
                .thenReturn(Optional.of(address));

        Address found = addressService.getaddressbyid(1);

        assertEquals(560001, found.getPincode());
    }

    @Test
    void getAddressById_notFound_shouldThrowException() {
        when(addressdao.getaddressbyid(1))
                .thenReturn(Optional.empty());

        assertThrows(IdNotFoundException.class,
                () -> addressService.getaddressbyid(1));
    }



    @Test
    void getAllAddress_success() {
        List<Address> list = List.of(address);
        when(addressdao.getAllAddress()).thenReturn(list);

        List<Address> result = addressService.getAllAddress();

        assertEquals(1, result.size());
        verify(addressdao).getAllAddress();
    }
}