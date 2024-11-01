import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartementServiceImplTest {

    @InjectMocks
    private DepartementServiceImpl departementService;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    void setUp() {
        departementRepository = mock(DepartementRepository.class);
        departementService = new DepartementServiceImpl(departementRepository);
    }

    @Test
    void testRetrieveAllDepartements() {
        Departement departement1 = new Departement();
        Departement departement2 = new Departement();
        when(departementRepository.findAll()).thenReturn(Arrays.asList(departement1, departement2));

        List<Departement> result = departementService.retrieveAllDepartements();

        assertEquals(2, result.size());
        verify(departementRepository, times(1)).findAll();
    }

    @Test
    void testAddDepartement() {
        Departement departement = new Departement();
        when(departementRepository.save(departement)).thenReturn(departement);

        Departement result = departementService.addDepartement(departement);

        assertNotNull(result);
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    void testUpdateDepartement() {
        Departement departement = new Departement();
        when(departementRepository.save(departement)).thenReturn(departement);

        Departement result = departementService.updateDepartement(departement);

        assertNotNull(result);
        verify(departementRepository, times(1)).save(departement);
    }

    @Test
    void testRetrieveDepartement() {
        Integer idDepart = 1;
        Departement departement = new Departement();
        when(departementRepository.findById(idDepart)).thenReturn(Optional.of(departement));

        Departement result = departementService.retrieveDepartement(idDepart);

        assertNotNull(result);
        assertEquals(departement, result);
        verify(departementRepository, times(1)).findById(idDepart);
    }

    @Test
    void testRetrieveDepartementNotFound() {
        Integer idDepart = 1;
        when(departementRepository.findById(idDepart)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> departementService.retrieveDepartement(idDepart));
        assertEquals("Departement not found with id: " + idDepart, exception.getMessage());
        verify(departementRepository, times(1)).findById(idDepart);
    }

    @Test
    void testDeleteDepartement() {
        Integer idDepartement = 1;
        Departement departement = new Departement();
        when(departementRepository.findById(idDepartement)).thenReturn(Optional.of(departement));

        departementService.deleteDepartement(idDepartement);

        verify(departementRepository, times(1)).delete(departement);
    }

    @Test
    void testDeleteDepartementNotFound() {
        Integer idDepartement = 1;
        when(departementRepository.findById(idDepartement)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> departementService.deleteDepartement(idDepartement));
        assertEquals("Departement not found with id: " + idDepartement, exception.getMessage());
        verify(departementRepository, never()).delete(any());
    }
}
