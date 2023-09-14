package com.example.demo.serviceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import com.example.demo.dto.SupplierInputDTO;
import com.example.demo.dto.SupplierOutputDTO;
import com.example.demo.entity.Supplier;
import com.example.demo.entity.SupplierBuilder;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.SupplierService;

import java.util.List;
import java.util.Optional;

@TestPropertySource(locations = "classpath:application-test.properties")
public class SupplierServiceTest {
	
	private SupplierService supplierService;
    private SupplierRepository supplierRepository;
    private Supplier supplier;
    private Supplier mockSupplier;
	public static SupplierOutputDTO createdSupplierOutputDTO;
	public static SupplierInputDTO mockSupplierInputDTO;
	public static SupplierOutputDTO updatedSupplierOutputDTO;
    
    public static final long SUPPLIER_ID1 = 1;
	public static final String SUPPLIER_NAME1 = "Supplier1";
	public static final String SUPPLIER_ADDRESS1 = "469";
	public static final String SUPPLIER_CONTACT1 = "07744876755";
	public static final String SUPPLIER_SPECIALTIES1 = "hardware";
	public static final long SUPPLIER_ID2 = 106;
	public static final String SUPPLIER_NAME2 = "Supplier2";
	public static final String SUPPLIER_ADDRESS2 = "235";
	public static final String SUPPLIER_CONTACT2 = "07844876755";
	public static final String SUPPLIER_SPECIALTIES2 = "software";
	public static final long NON_EXISTING_SUPPLIER_ID = 999;
	public static final long SUPPLIER_ID3 = 2;
    
	@BeforeEach
    public void setUp() {
        supplierRepository = mock(SupplierRepository.class);
        supplierService = new SupplierService(supplierRepository);
        supplier = new SupplierBuilder()
                .id(SUPPLIER_ID1)
                .name(SUPPLIER_NAME1)
                .address(SUPPLIER_ADDRESS1)
                .contactDetails(SUPPLIER_CONTACT1)
                .specialties(SUPPLIER_SPECIALTIES1)
                .build();
        mockSupplier = new Supplier();
        mockSupplier.setId(SUPPLIER_ID2); 
        mockSupplier.setName(SUPPLIER_NAME2);
        mockSupplier.setAddress(SUPPLIER_ADDRESS2);
        mockSupplier.setContactDetails(SUPPLIER_CONTACT2);
        mockSupplier.setSpecialties(SUPPLIER_SPECIALTIES2);
        mockSupplierInputDTO = new SupplierInputDTO(SUPPLIER_NAME2, SUPPLIER_ADDRESS2, SUPPLIER_CONTACT2, SUPPLIER_SPECIALTIES2);
        updatedSupplierOutputDTO = new SupplierOutputDTO(SUPPLIER_ID2, SUPPLIER_NAME2, SUPPLIER_ADDRESS2, SUPPLIER_CONTACT2,
				SUPPLIER_SPECIALTIES2);
	}

    @Test
    public void testGetAllSuppliers() {

            when(supplierRepository.findAll()).thenReturn(Arrays.asList(supplier));

            List<SupplierOutputDTO> suppliers = supplierService.getAllSuppliers();

            assertEquals(1, suppliers.size());
            assertEquals(SUPPLIER_NAME1, suppliers.get(0).getName());
            assertEquals(SUPPLIER_ADDRESS1, suppliers.get(0).getAddress());
            assertEquals(SUPPLIER_CONTACT1, suppliers.get(0).getContactDetails());
            assertEquals(SUPPLIER_SPECIALTIES1, suppliers.get(0).getSpecialties());
        }

    @Test
    public void testGetSupplierById_ExistingSupplier() {

            when(supplierRepository.findById(SUPPLIER_ID1)).thenReturn(Optional.of(supplier));

            SupplierOutputDTO result = supplierService.getSupplierById(SUPPLIER_ID1);

            assertNotNull(result);
            assertEquals(SUPPLIER_NAME1, result.getName());
            assertEquals(SUPPLIER_ADDRESS1, result.getAddress());
            assertEquals(SUPPLIER_CONTACT1, result.getContactDetails());
            assertEquals(SUPPLIER_SPECIALTIES1, result.getSpecialties());
        }

    @Test
    public void testGetSupplierById_NonExistentSupplier() {

        when(supplierRepository.findById(NON_EXISTING_SUPPLIER_ID)).thenReturn(java.util.Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> supplierService.getSupplierById(NON_EXISTING_SUPPLIER_ID));
    }
    
    @Test
    public void testDeleteSupplier() {
        supplierService.deleteSupplier(SUPPLIER_ID1);

        verify(supplierRepository, times(1)).deleteById(SUPPLIER_ID1);
    }

    @Test
    public void testExistsSupplierWithId_ExistingSupplier() {
        when(supplierRepository.existsById(SUPPLIER_ID1)).thenReturn(true);

        assertTrue(supplierService.existsSupplierWithId(SUPPLIER_ID1));
    }

    @Test
    public void testExistsSupplierWithId_NonExistentSupplier() {
        when(supplierRepository.existsById(NON_EXISTING_SUPPLIER_ID)).thenReturn(false);
        
        assertFalse(supplierService.existsSupplierWithId(NON_EXISTING_SUPPLIER_ID));
    }
}
