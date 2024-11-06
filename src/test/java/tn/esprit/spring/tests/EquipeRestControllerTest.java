package tn.esprit.spring.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.kaddem.controllers.EquipeRestController;
import tn.esprit.spring.kaddem.dto.EquipeDTO;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.services.IEquipeService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EquipeRestControllerTest {

    @Mock
    private IEquipeService equipeService;

    @InjectMocks
    private EquipeRestController equipeRestController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(equipeRestController).build();
        objectMapper = new ObjectMapper().configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
    }

    @Test
    void testGetEquipes() throws Exception {
        Equipe equipe1 = new Equipe();
        equipe1.setIdEquipe(1);
        equipe1.setNomEquipe("Equipe1");
        equipe1.setNiveau(Niveau.JUNIOR);

        Equipe equipe2 = new Equipe();
        equipe2.setIdEquipe(2);
        equipe2.setNomEquipe("Equipe2");
        equipe2.setNiveau(Niveau.JUNIOR);

        List<Equipe> equipes = Arrays.asList(equipe1, equipe2);

        when(equipeService.retrieveAllEquipes()).thenReturn(equipes);

        mockMvc.perform(get("/equipe/retrieve-all-equipes"))
                .andExpect(status().isOk())
                .andDo(print())  // This will print the raw response to the console
                .andExpect(jsonPath("$[0].nomEquipe").value("Equipe1"))
                .andExpect(jsonPath("$[1].nomEquipe").value("Equipe2"));


        verify(equipeService, times(1)).retrieveAllEquipes();
    }

    @Test
    void testRetrieveEquipe() throws Exception {
        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setNomEquipe("Equipe1");
        equipe.setNiveau(Niveau.JUNIOR);

        when(equipeService.retrieveEquipe(1)).thenReturn(equipe);

        mockMvc.perform(get("/equipe/retrieve-equipe/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomEquipe").value("Equipe1"));

        verify(equipeService, times(1)).retrieveEquipe(1);
    }

    @Test
    void testAddEquipe() throws Exception {
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setNomEquipe("Equipe1");
        equipeDTO.setNiveau(Niveau.JUNIOR);  // This uses the enum constant, which will map to "JUNIOR"

        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setNomEquipe("Equipe1");
        equipe.setNiveau(Niveau.JUNIOR);

        when(equipeService.addEquipe(any(Equipe.class))).thenReturn(equipe);

        mockMvc.perform(post("/equipe/add-equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(equipeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomEquipe").value("Equipe1"))
                .andExpect(jsonPath("$.niveau").value(Matchers.equalTo("JUNIOR")));  // Expect "JUNIOR"

        verify(equipeService, times(1)).addEquipe(any(Equipe.class));
    }


    @Test
    void testRemoveEquipe() throws Exception {
        mockMvc.perform(delete("/equipe/remove-equipe/1"))
                .andExpect(status().isOk());

        verify(equipeService, times(1)).deleteEquipe(1);
    }

    @Test
    void testUpdateEquipe() throws Exception {
        EquipeDTO equipeDTO = new EquipeDTO();
        equipeDTO.setNomEquipe("Equipe1");
        equipeDTO.setNiveau(Niveau.EXPERT);

        Equipe equipe = new Equipe();
        equipe.setIdEquipe(1);
        equipe.setNomEquipe("Equipe1");
        equipe.setNiveau(Niveau.EXPERT);

        when(equipeService.updateEquipe(any(Equipe.class))).thenReturn(equipe);

        mockMvc.perform(put("/equipe/update-equipe")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(equipeDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomEquipe").value("Equipe1"))
                .andExpect(jsonPath("$.niveau").value(Matchers.equalTo("EXPERT"))); // Fix here, expecting the correct casing

        verify(equipeService, times(1)).updateEquipe(any(Equipe.class));
    }


}