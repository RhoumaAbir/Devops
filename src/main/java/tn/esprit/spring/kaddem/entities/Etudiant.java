package tn.esprit.spring.kaddem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@SuppressWarnings("SpellCheckingInspection")
@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Etudiant implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idEtudiant;
    private String nomE;
    private String prenomE;
    @Enumerated(EnumType.STRING)
    private Option op;
    @OneToMany(mappedBy="etudiant", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Contrat> contrats;
    @ManyToOne
    @JsonIgnore
    private Departement departement;
    @ManyToMany(mappedBy="etudiants")

    @JsonIgnore
    private List<Equipe> equipes ;
}
