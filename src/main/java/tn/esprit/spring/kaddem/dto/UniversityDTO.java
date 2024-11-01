package tn.esprit.spring.kaddem.dto;


import lombok.Getter;
import lombok.Setter;
import tn.esprit.spring.kaddem.entities.Departement;


import java.util.Set;


@Getter
@Setter
public class UniversityDTO {
    private String nomUniv;
    private Set<Departement> departements;
}
