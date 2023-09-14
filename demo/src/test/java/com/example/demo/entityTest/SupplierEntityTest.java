package com.example.demo.entityTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.example.demo.dto.SupplierOutputDTO;
import com.example.demo.entity.Supplier;

@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
public class SupplierEntityTest {

	public static final long SUPPLIER_ID = 1;
	public static final String SUPPLIER_NAME = "Test Supplier";
	public static final String SUPPLIER_ADDRESS = "123 Test St";
	public static final String SUPPLIER_CONTACT = "test@example.com";
	public static final String SUPPLIER_SPECIALTIES = "Test Specialties";
	
	private Supplier supplier;
	
	@BeforeEach
	public void setup() {
		supplier = new Supplier();
		supplier.setId(SUPPLIER_ID);
		supplier.setName(SUPPLIER_NAME);
        supplier.setAddress(SUPPLIER_ADDRESS);
        supplier.setContactDetails(SUPPLIER_CONTACT);
        supplier.setSpecialties(SUPPLIER_SPECIALTIES);
	}
	
    @Test
    public void testSupplierEntityCreation() {

        assertEquals(SUPPLIER_NAME, supplier.getName());
        assertEquals(SUPPLIER_ADDRESS, supplier.getAddress());
        assertEquals(SUPPLIER_CONTACT, supplier.getContactDetails());
        assertEquals(SUPPLIER_SPECIALTIES, supplier.getSpecialties());
    }

    @Test
    public void testSupplierOutputDTOConversion() {

        SupplierOutputDTO outputDTO = supplier.viewAsOutputDto();

        assertNotNull(outputDTO);
        assertEquals(SUPPLIER_ID, outputDTO.getId());
        assertEquals(SUPPLIER_NAME, outputDTO.getName());
        assertEquals(SUPPLIER_ADDRESS, outputDTO.getAddress());
        assertEquals(SUPPLIER_CONTACT, outputDTO.getContactDetails());
        assertEquals(SUPPLIER_SPECIALTIES, outputDTO.getSpecialties());
    }
}
