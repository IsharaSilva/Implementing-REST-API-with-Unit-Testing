package com.example.demo.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.entity.Supplier;
import com.example.demo.repository.SupplierRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class SupplierRepositoryTest {

	@Autowired
    private SupplierRepository supplierRepository;
	
	public static final long NON_EXISTING_SUPPLIER_ID = -1;
	public static final long SUPPLIER_ID = 1;
	public static final String SUPPLIER_NAME = "Supplier1";
	
	private Supplier supplier;
	
	@BeforeEach
	public void setup() {
		supplier = new Supplier();
		supplier.setName(SUPPLIER_NAME);
	}
	

    @Test
    public void testExistsById_ExistingId_ReturnsTrue() {
       
        supplierRepository.save(supplier);

        boolean exists = supplierRepository.existsById(supplier.getId());

        assertTrue(exists);
    }

    @Test
    public void testExistsById_NonExistingId_ReturnsFalse() {
        boolean exists = supplierRepository.existsById(NON_EXISTING_SUPPLIER_ID); 

        assertFalse(exists);
    }
}
