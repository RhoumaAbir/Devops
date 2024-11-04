package tn.esprit.spring.kaddem.entities;

import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@ToString
public class Contrat implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idContrat;
    @Temporal(TemporalType.DATE)
    private Date dateDebutContrat;
    @Temporal(TemporalType.DATE)
    private Date dateFinContrat;
    private Boolean archive;
    private Integer montantContrat;


    public Contrat() {
        // TODO Auto-generated constructor stub
    }

    public Contrat(Date dateDebutContrat, Date dateFinContrat, Boolean archive,
                   Integer montantContrat) {
        super();
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;
        this.archive = archive;
        this.montantContrat = montantContrat;
    }

    public Contrat(Integer idContrat, Date dateDebutContrat, Date dateFinContrat,
                   Boolean archive, Integer montantContrat) {
        super();
        this.idContrat = idContrat;
        this.dateDebutContrat = dateDebutContrat;
        this.dateFinContrat = dateFinContrat;

        this.archive = archive;
        this.montantContrat = montantContrat;
    }

    public Integer getIdContrat() {
        return idContrat;
    }
    public void setIdContrat(Integer idContrat) {
        this.idContrat = idContrat;
    }
    public Date getDateDebutContrat() {
        return dateDebutContrat;
    }
    public void setDateDebutContrat(Date dateDebutContrat) {
        this.dateDebutContrat = dateDebutContrat;
    }
    public Date getDateFinContrat() {
        return dateFinContrat;
    }
    public void setDateFinContrat(Date dateFinContrat) {
        this.dateFinContrat = dateFinContrat;
    }

    public Boolean getArchive() {
        return archive;
    }
    public void setArchive(Boolean archive) {
        this.archive = archive;
    }
    public Integer getMontantContrat() {
        return montantContrat;
    }
    public void setMontantContrat(Integer montantContrat) {
        this.montantContrat = montantContrat;
    }




}
