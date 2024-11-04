package tn.esprit.spring.kaddem.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UniversiteServiceImplTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    void setUp() {
        universiteRepository = mock(UniversiteRepository.class);
        departementRepository = mock(DepartementRepository.class);
        universiteService = new UniversiteServiceImpl(universiteRepository, departementRepository);
    }

    @Test
    void testRetrieveAllUniversites() {
        Universite universite1 = new Universite();
        Universite universite2 = new Universite();
        when(universiteRepository.findAll()).thenReturn(Arrays.asList(universite1, universite2));

        List<Universite> result = universiteService.retrieveAllUniversites();

        assertEquals(2, result.size());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testAddUniversite() {
        Universite universite = new Universite();
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);

        assertNotNull(result);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testUpdateUniversite() {
        Universite universite = new Universite();
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.updateUniversite(universite);

        assertNotNull(result);
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRetrieveUniversite() {
        Integer idUniversite = 1;
        Universite universite = new Universite();
        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(idUniversite);

        assertNotNull(result);
        assertEquals(universite, result);
        verify(universiteRepository, times(1)).findById(idUniversite);
    }

    @Test
    void testRetrieveUniversiteNotFound() {
        Integer idUniversite = 1;
        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> universiteService.retrieveUniversite(idUniversite));
        assertEquals("University with id not found", exception.getMessage());
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
        universite.setDepartements(new HashSet<>());

        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(idDepartement)).thenReturn(Optional.of(departement));

        universiteService.assignUniversiteToDepartement(idUniversite, idDepartement);

        assertTrue(universite.getDepartements().contains(departement));
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRetrieveDepartementsByUniversite() {
        Integer idUniversite = 1;
        Universite universite = new Universite();
        Set<Departement> departements = new HashSet<>();
        departements.add(new Departement());
        universite.setDepartements(departements);

        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.of(universite));

        Set<Departement> result = universiteService.retrieveDepartementsByUniversite(idUniversite);

        assertEquals(departements, result);
        verify(universiteRepository, times(1)).findById(idUniversite);
    }

    @Test
    void testRetrieveDepartementsByUniversiteNotFound() {
        Integer idUniversite = 1;
        when(universiteRepository.findById(idUniversite)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> universiteService.retrieveDepartementsByUniversite(idUniversite));
        assertEquals("University with id " + idUniversite + " not found", exception.getMessage());
        verify(universiteRepository, times(1)).findById(idUniversite);
    }
}
