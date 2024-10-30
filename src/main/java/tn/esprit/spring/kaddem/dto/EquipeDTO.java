package tn.esprit.spring.kaddem.dto;

import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.kaddem.entities.Niveau;

@Getter
@Setter
public class EquipeDTO {
    private String nomEquipe;
    private Niveau niveau;
}
