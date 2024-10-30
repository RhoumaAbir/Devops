package tn.esprit.spring.kaddem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class Equipe implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idEquipe;
    private String nomEquipe;
    @Enumerated(EnumType.STRING)
    private Niveau niveau;
    //@ManyToMany(mappedBy="equipes")
    @ManyToMany(cascade =CascadeType.ALL)

    @JsonIgnore
    private Set<Etudiant> etudiants;
    @OneToOne
    private DetailEquipe detailEquipe;

    public Equipe() {
    }

    public Equipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public Equipe(String nomEquipe, Niveau niveau) {
        super();
        this.nomEquipe = nomEquipe;
        this.niveau = niveau;
    }

    public Equipe(Integer idEquipe, String nomEquipe, Niveau niveau) {
        super();
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.niveau = niveau;
    }

    public Equipe(String nomEquipe, Niveau niveau, Set<Etudiant> etudiants, DetailEquipe detailEquipe) {
        this.nomEquipe = nomEquipe;
        this.niveau = niveau;
        this.etudiants = etudiants;
        this.detailEquipe = detailEquipe;
    }

    public Equipe(Integer idEquipe, String nomEquipe, Niveau niveau, Set<Etudiant> etudiants, DetailEquipe detailEquipe) {
        this.idEquipe = idEquipe;
        this.nomEquipe = nomEquipe;
        this.niveau = niveau;
        this.etudiants = etudiants;
        this.detailEquipe = detailEquipe;
    }

    public DetailEquipe getDetailEquipe() {
        return detailEquipe;
    }

    public void setDetailEquipe(DetailEquipe detailEquipe) {
        this.detailEquipe = detailEquipe;
    }

}
