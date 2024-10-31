package tn.esprit.spring.kaddem.entities;

import lombok.*;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetailEquipe implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idDetailEquipe;
    private Integer salle;
    private String thematique;
    @OneToOne(mappedBy="detailEquipe")
    private Equipe equipe;
}
