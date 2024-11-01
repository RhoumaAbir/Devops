package tn.esprit.spring.kaddem.services;


import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class UniversiteServiceImpl implements IUniversiteService{
    private final UniversiteRepository universiteRepository;
    private final DepartementRepository departementRepository;


    public UniversiteServiceImpl(UniversiteRepository universiteRepository, DepartementRepository departementRepository) {
        this.universiteRepository = universiteRepository;
        this.departementRepository = departementRepository;
    }
    public   List<Universite> retrieveAllUniversites(){
        return (List<Universite>) universiteRepository.findAll();
    }

    public    Universite addUniversite (Universite  u){
        return  (universiteRepository.save(u));
    }

    public    Universite updateUniversite (Universite  u){
        return  (universiteRepository.save(u));
    }

    public Universite retrieveUniversite(Integer idUniversite) {
        return universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new NoSuchElementException("University with id not found"));
    }

    public  void deleteUniversite(Integer idUniversite){
        universiteRepository.delete(retrieveUniversite(idUniversite));
    }

    public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement) {
        Universite u = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new NoSuchElementException("University with id not found"));
        Departement d = departementRepository.findById(idDepartement)
                .orElseThrow(() -> new NoSuchElementException("Department with id " + idDepartement + " not found"));

        u.getDepartements().add(d);
        universiteRepository.save(u);
    }

    public Set<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        Universite u = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new NoSuchElementException("University with id " + idUniversite + " not found"));

        return u.getDepartements();
    }

}