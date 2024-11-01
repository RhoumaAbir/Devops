package tn.esprit.spring.kaddem.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UniversiteServiceImplTest {

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllUniversites() {
        List<Universite> universites = new ArrayList<>();
        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> result = universiteService.retrieveAllUniversites();
        assertEquals(universites, result);
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testAddUniversite() {
        Universite universite = new Universite();
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);
        assertEquals(universite, result);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testUpdateUniversite() {
        Universite universite = new Universite();
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.updateUniversite(universite);
        assertEquals(universite, result);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRetrieveUniversite_Found() {
        Integer idUniversite = 1;
        Universite universite = new Universite();
        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(idUniversite);
        assertEquals(universite, result);
        verify(universiteRepository, times(1)).findById(idUniversite);
    }

    @Test
    void testRetrieveUniversite_NotFound() {
        Integer idUniversite = 1;
        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> universiteService.retrieveUniversite(idUniversite));
        verify(universiteRepository, times(1)).findById(idUniversite);
    }

    @Test
    void testDeleteUniversite() {
        Integer idUniversite = 1;
        Universite universite = new Universite();
        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));

        universiteService.deleteUniversite(idUniversite);
        verify(universiteRepository, times(1)).delete(universite);
    }

    @Test
    void testAssignUniversiteToDepartement() {
        Integer idUniversite = 1;
        Integer idDepartement = 2;
        Universite universite = new Universite();
        Departement departement = new Departement();

        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(idDepartement)).thenReturn(Optional.of(departement));

        universiteService.assignUniversiteToDepartement(idUniversite, idDepartement);
        assertTrue(universite.getDepartements().contains(departement));
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRetrieveDepartementsByUniversite_Found() {
        Integer idUniversite = 1;
        Universite universite = new Universite();
        Set<Departement> departements = new HashSet<>();
        universite.setDepartements(departements);

        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));

        Set<Departement> result = universiteService.retrieveDepartementsByUniversite(idUniversite);
        assertEquals(departements, result);
        verify(universiteRepository, times(1)).findById(idUniversite);
    }

    @Test
    void testRetrieveDepartementsByUniversite_NotFound() {
        Integer idUniversite = 1;
        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> universiteService.retrieveDepartementsByUniversite(idUniversite));
        verify(universiteRepository, times(1)).findById(idUniversite);
    }
}
