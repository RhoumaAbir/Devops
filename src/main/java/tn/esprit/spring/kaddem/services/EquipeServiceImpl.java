package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class EquipeServiceImpl implements IEquipeService{
	EquipeRepository equipeRepository;


	public List<Equipe> retrieveAllEquipes(){
	return  (List<Equipe>) equipeRepository.findAll();
	}
	public Equipe addEquipe(Equipe e){
		return (equipeRepository.save(e));
	}

	public void deleteEquipe(Integer idEquipe) {
		Optional<Equipe> equipe = equipeRepository.findById(idEquipe);
		if (equipe.isEmpty()) {
			throw new IllegalArgumentException("Equipe not found with id: " + idEquipe);
		}
		equipeRepository.delete(equipe.get());
	}


	public Equipe retrieveEquipe(Integer equipeId){
		return equipeRepository.findById(equipeId).orElse(null);
	}

	public Equipe updateEquipe(Equipe e){
	return (	equipeRepository.save(e));
	}

	public void evoluerEquipes() {
		Iterable<Equipe> equipesIterable = equipeRepository.findAll();
		List<Equipe> equipes = new ArrayList<>();
		equipesIterable.forEach(equipes::add);

		for (Equipe equipe : equipes) {
			if (canPromoteEquipe(equipe)) {
				equipe.setNiveau(Niveau.SENIOR);
				equipeRepository.save(equipe);
			}
		}
	}

	private boolean canPromoteEquipe(Equipe equipe) {
		if (equipe.getNiveau() != Niveau.JUNIOR) {
			return false; // No need to check further if not JUNIOR
		}

		for (Etudiant etudiant : equipe.getEtudiants()) {
			for (Contrat contrat : etudiant.getContrats()) {
				if (!contrat.isArchived() && contrat.getDateFinContrat().after(new Date())) {
					return true; // Found a valid contract
				}
			}
		}
		return false; // No valid contracts found
	}



}