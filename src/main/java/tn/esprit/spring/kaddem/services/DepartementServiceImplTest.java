package tn.esprit.spring.kaddem.services;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartementServiceImplTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    private Departement departement;

    @BeforeEach
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Sample Departement instance
        departement = new Departement();
        departement.setIdDepart(1);
        departement.setNomDepart("Informatique");
    }

    @Test
    public void testRetrieveAllDepartements() {
        // Given
        List<Departement> departements = new ArrayList<>();
        departements.add(departement);
        when(departementRepository.findAll()).thenReturn(departements);

        // When
        List<Departement> result = departementService.retrieveAllDepartements();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(departement.getNomDepart(), result.get(0).getNomDepart());
        verify(departementRepository, times(1)).findAll();
    }

    @Test
    public void testAddDepartement() {
        // Given
        when(departementRepository.save(departement)).thenReturn(departement);

        // When
        Departement savedDepartement = departementService.addDepartement(departement);

        // Then
        assertNotNull(savedDepartement);
        assertEquals(departement.getIdDepart(), savedDepartement.getIdDepart());
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    public void testUpdateDepartement() {
        // Given
        when(departementRepository.save(departement)).thenReturn(departement);

        // When
        Departement updatedDepartement = departementService.updateDepartement(departement);

        // Then
        assertNotNull(updatedDepartement);
        assertEquals(departement.getIdDepart(), updatedDepartement.getIdDepart());
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    public void testRetrieveDepartement() {
        // Given
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        // When
        Departement retrievedDepartement = departementService.retrieveDepartement(1);

        // Then
        assertNotNull(retrievedDepartement);
        assertEquals(departement.getNomDepart(), retrievedDepartement.getNomDepart());
        verify(departementRepository, times(1)).findById(1);
    }

    @Test
    public void testDeleteDepartement() {
        // Given
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        // When
        departementService.deleteDepartement(1);

        // Then
        verify(departementRepository, times(1)).findById(1);
        verify(departementRepository, times(1)).delete(departement);
    }
}

