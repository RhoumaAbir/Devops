package tn.esprit.spring.kaddem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Setter
@Getter
@Entity
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
  //  @ManyToMany(cascade =CascadeType.ALL)
    @ManyToMany(mappedBy="etudiants")

    @JsonIgnore
    private List<Equipe> equipes ;
    public Etudiant() {
    }

    public Etudiant(String nomE, String prenomE) {
        this.nomE = nomE;
        this.prenomE = prenomE;
    }

    public Etudiant(String nomE, String prenomE, Option op) {
        super();
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.op = op;
    }

    public Etudiant(Integer idEtudiant, String nomE, String prenomE, Option op) {
        super();
        this.idEtudiant = idEtudiant;
        this.nomE = nomE;
        this.prenomE = prenomE;
        this.op = op;
    }

}
