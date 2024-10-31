import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.repositories.EtudiantRepository;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class EtudiantServiceImplTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private ContratRepository contratRepository;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant;

    @BeforeEach
    void setUp() {
        etudiant = new Etudiant();
    }

    @Test
    @Order(1)
    void testRetrieveAllEtudiants() {
        when(etudiantRepository.findAll()).thenReturn(Collections.singletonList(etudiant));
        var result = etudiantService.retrieveAllEtudiants();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    @Order(2)
    void testAddEtudiant() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);
        Etudiant saved = etudiantService.addEtudiant(etudiant);
        assertNotNull(saved);
    }

    @Test
    @Order(3)
    void testRetrieveEtudiant() {
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));
        Etudiant found = etudiantService.retrieveEtudiant(1);
        assertNotNull(found);
    }
    @Test
    @Order(4)
    void testUpdateEtudiant() {
        when(etudiantRepository.save(any(Etudiant.class))).thenReturn(etudiant);
        Etudiant updated = etudiantService.updateEtudiant(etudiant);
        assertNotNull(updated);
    }

    @Test
    @Order(5)
    void testRemoveEtudiant() {
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));
        etudiantService.removeEtudiant(1);
        verify(etudiantRepository, times(1)).delete(etudiant);
    }

    @Test
    @Order(6)
    void testAssignEtudiantToDepartement() {
        when(etudiantRepository.findById(1)).thenReturn(Optional.of(etudiant));
        Departement departement = new Departement(); // You may want to initialize with necessary fields
        when(departementRepository.findById(1)).thenReturn(Optional.of(departement));

        etudiantService.assignEtudiantToDepartement(1, 1);
        assertEquals(departement, etudiant.getDepartement());
        verify(etudiantRepository, times(1)).save(etudiant);
    }

    @Test
    @Order(7)
    void testAddAndAssignEtudiantToEquipeAndContract() {
        Contrat contrat = new Contrat();
        when(contratRepository.findById(1)).thenReturn(Optional.of(contrat));

        Equipe equipe = new Equipe();
        equipe.setEtudiants(new HashSet<>());
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));

        Etudiant assigned = etudiantService.addAndAssignEtudiantToEquipeAndContract(etudiant, 1, 1);

        assertNotNull(assigned);
        assertEquals(etudiant, assigned);
        assertTrue(equipe.getEtudiants().contains(etudiant));
    }


    @Test
    @Order(8)
    void testGetEtudiantsByDepartement() {
        when(etudiantRepository.findEtudiantsByDepartement_IdDepart(1)).thenReturn(Collections.singletonList(etudiant));
        List<Etudiant> result = etudiantService.getEtudiantsByDepartement(1);
        assertNotNull(result);
        assertEquals(1, result.size());
    }


}
