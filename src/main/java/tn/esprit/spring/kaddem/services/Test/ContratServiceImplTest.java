package tn.esprit.spring.kaddem.services.Test;


import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.repositories.ContratRepository;
import tn.esprit.spring.kaddem.services.ContratServiceImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ContratServiceImplTest {

    @InjectMocks
    private ContratServiceImpl contratService;

    @Mock
    private ContratRepository contratRepository;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllContrats() {
        Contrat c1 = new Contrat();
        Contrat c2 = new Contrat();
        List<Contrat> contrats = Arrays.asList(c1, c2);

        Mockito.when(contratRepository.findAll()).thenReturn(contrats);

        List<Contrat> result = contratService.retrieveAllContrats();

        Assertions.assertEquals(2, result.size());
        Mockito.verify(contratRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testAddContrat() {
        Contrat c = new Contrat();

        Mockito.when(contratRepository.save(c)).thenReturn(c);

        Contrat result = contratService.addContrat(c);

        Assertions.assertEquals(c, result);
        Mockito.verify(contratRepository, Mockito.times(1)).save(c);
    }

    @Test
    void testUpdateContrat() {
        Contrat c = new Contrat();

        Mockito.when(contratRepository.save(c)).thenReturn(c);

        Contrat result = contratService.updateContrat(c);

        Assertions.assertEquals(c, result);
        Mockito.verify(contratRepository, Mockito.times(1)).save(c);
    }

    @Test
    void testRetrieveContrat() {
        Integer idContrat = 1;
        Contrat c = new Contrat();

        Mockito.when(contratRepository.findById(idContrat)).thenReturn(Optional.of(c));

        Contrat result = contratService.retrieveContrat(idContrat);

        Assertions.assertEquals(c, result);
        Mockito.verify(contratRepository, Mockito.times(1)).findById(idContrat);
    }

    @Test
    void testRemoveContrat() {
        Integer idContrat = 1;
        Contrat c = new Contrat();

        Mockito.when(contratRepository.findById(idContrat)).thenReturn(Optional.of(c));

        contratService.removeContrat(idContrat);

        Mockito.verify(contratRepository, Mockito.times(1)).delete(c);
    }


}
