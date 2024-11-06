package tn.esprit.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEtudiant;
    private String nomE;
    private String prenomE;

    @Enumerated(EnumType.STRING)
    private Option op;

    // One-to-many relationship with Contrat
    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Contrat> contrats;

    // Many-to-one relationship with Departement
    @ManyToOne
    @JsonIgnore
    private Departement departement;

    // Many-to-many relationship with Equipe
    @ManyToMany(mappedBy = "etudiants")
    @JsonIgnore
    private List<Equipe> equipes;
}
