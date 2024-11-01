package tn.esprit.spring.kaddem;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.DepartementServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 class DepartementServiceImplTest {

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementServiceImpl departementService;

    private Departement departement;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        departement = new Departement();
        departement.setIdDepart(1);
        departement.setNomDepart("Informatique");
    }

    @Test
      void testRetrieveAllDepartements() {
        List<Departement> departements = new ArrayList<>();
        departements.add(departement);
        when(departementRepository.findAll()).thenReturn(departements);

        departementService.retrieveAllDepartements();

        verify(departementRepository, times(1)).findAll();
    }

    @Test
     void testAddDepartement() {
        when(departementRepository.save(departement)).thenReturn(departement);

        departementService.addDepartement(departement);

        verify(departementRepository, times(1)).save(departement);
    }

    @Test
     void testUpdateDepartement() {
        when(departementRepository.save(departement)).thenReturn(departement);

        departementService.updateDepartement(departement);

        verify(departementRepository, times(1)).save(departement);
    }

    @Test
     void testRetrieveDepartement() {
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        departementService.retrieveDepartement(1);

        verify(departementRepository, times(1)).findById(1);
    }

    @Test
     void testRetrieveDepartementNotFound() {
        when(departementRepository.findById(1)).thenReturn(Optional.empty());

        departementService.retrieveDepartement(1);

        verify(departementRepository, times(1)).findById(1);
    }


    @Test
     void testDeleteDepartement() {
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        departementService.deleteDepartement(1);

        verify(departementRepository, times(1)).findById(1);
        verify(departementRepository, times(1)).delete(departement);
    }

    @Test
     void testDeleteDepartementNotFound() {
        when(departementRepository.findById(1)).thenReturn(Optional.empty());

        departementService.deleteDepartement(1);

        verify(departementRepository, times(1)).findById(1);
        verify(departementRepository, never()).delete(any(Departement.class)); // Ensure delete was not called
    }
}
