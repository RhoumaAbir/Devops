import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;

import java.util.*;

class EquipeServiceImplTest {

    @InjectMocks
    private EquipeServiceImpl equipeService;

    @Mock
    private EquipeRepository equipeRepository;

    @BeforeEach
    void setUp() {
        equipeRepository = mock(EquipeRepository.class);
        equipeService = new EquipeServiceImpl(equipeRepository);
    }


    @Test
    void testRetrieveAllEquipes() {
        Equipe equipe1 = new Equipe();
        Equipe equipe2 = new Equipe();
        when(equipeRepository.findAll()).thenReturn(Arrays.asList(equipe1, equipe2));

        var result = equipeService.retrieveAllEquipes();

        assertEquals(2, result.size());
        verify(equipeRepository, times(1)).findAll();
    }

    @Test
    void testAddEquipe() {
        Equipe equipe = new Equipe();
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        var result = equipeService.addEquipe(equipe);

        assertNotNull(result);
        verify(equipeRepository, times(1)).save(equipe);
    }

    @Test
    void testDeleteEquipe() {
        Integer idEquipe = 1;
        Equipe equipe = new Equipe();
        when(equipeRepository.findById(idEquipe)).thenReturn(Optional.of(equipe));

        equipeService.deleteEquipe(idEquipe);

        verify(equipeRepository, times(1)).delete(equipe);
    }

    @Test
    void testRetrieveEquipe() {
        Integer idEquipe = 1;
        Equipe equipe = new Equipe();
        when(equipeRepository.findById(idEquipe)).thenReturn(Optional.of(equipe));

        var result = equipeService.retrieveEquipe(idEquipe);

        assertNotNull(result);
        assertEquals(equipe, result);
    }

    @Test
    void testUpdateEquipe() {
        Equipe equipe = new Equipe();
        when(equipeRepository.save(equipe)).thenReturn(equipe);

        var result = equipeService.updateEquipe(equipe);

        assertNotNull(result);
        verify(equipeRepository, times(1)).save(equipe);
    }

    @Test
    void testEvoluerEquipes() {
        // Setup
        Equipe equipe1 = new Equipe();
        equipe1.setNiveau(Niveau.JUNIOR);
        Equipe equipe2 = new Equipe();
        equipe2.setNiveau(Niveau.SENIOR);

        Etudiant etudiant1 = new Etudiant();
        Contrat contrat1 = new Contrat();
        contrat1.setArchived(false);
        contrat1.setDateFinContrat(new Date(System.currentTimeMillis() + (365 * 24 * 60 * 60 * 1000))); // 1 year in the future

        etudiant1.setContrats(new HashSet<>(Collections.singletonList(contrat1)));

        equipe1.setEtudiants(new HashSet<>(Collections.singletonList(etudiant1))); // Use only one etudiant

        when(equipeRepository.findAll()).thenReturn(Arrays.asList(equipe1, equipe2));
        when(equipeRepository.save(any(Equipe.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        equipeService.evoluerEquipes();

        assertEquals(Niveau.SENIOR, equipe1.getNiveau());
        verify(equipeRepository, times(1)).save(equipe1);
        verify(equipeRepository, never()).save(equipe2);
    }



    @Test
    void testDeleteEquipeNotFound() {
        Integer idEquipe = 1;
        when(equipeRepository.findById(idEquipe)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> equipeService.deleteEquipe(idEquipe));
        verify(equipeRepository, never()).delete(any());
    }

}