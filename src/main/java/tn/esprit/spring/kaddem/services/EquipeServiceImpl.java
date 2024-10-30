package tn.esprit.spring.kaddem.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.kaddem.entities.Contrat;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;

import java.util.Date;
import java.util.List;

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

	public  void deleteEquipe(Integer idEquipe){
		Equipe e=retrieveEquipe(idEquipe);
		equipeRepository.delete(e);
	}

	public Equipe retrieveEquipe(Integer equipeId){
		return equipeRepository.findById(equipeId).orElse(null);
	}

	public Equipe updateEquipe(Equipe e){
	return (	equipeRepository.save(e));
	}

	public void evoluerEquipes() {
		List<Equipe> equipes = (List<Equipe>) equipeRepository.findAll();
		for (Equipe equipe : equipes) {
			if (isLevelEligible(equipe)) {
				int nbEtudiantsAvecContratsActifs = countActiveStudentsWithContracts(equipe);
				updateEquipeLevel(equipe, nbEtudiantsAvecContratsActifs);
			}
		}
	}

	private boolean isLevelEligible(Equipe equipe) {
		return equipe.getNiveau().equals(Niveau.JUNIOR) || equipe.getNiveau().equals(Niveau.SENIOR);
	}

	private int countActiveStudentsWithContracts(Equipe equipe) {
		int nbEtudiantsAvecContratsActifs = 0;
		for (Etudiant etudiant : equipe.getEtudiants()) {
			if (hasActiveContract(etudiant)) {
				nbEtudiantsAvecContratsActifs++;
				if (nbEtudiantsAvecContratsActifs >= 3) {
					break; // Stop counting if we reach 3 active students
				}
			}
		}
		return nbEtudiantsAvecContratsActifs;
	}

	private boolean hasActiveContract(Etudiant etudiant) {
		return etudiant.getContrats().stream()
				.anyMatch(contrat -> !contrat.getArchive() && isContractExpired(contrat));
	}


	private boolean isContractExpired(Contrat contrat) {
		Date dateSysteme = new Date();
		long differenceInTime = dateSysteme.getTime() - contrat.getDateFinContrat().getTime();
		long differenceInYears = differenceInTime / (1000L * 60 * 60 * 24 * 365);
		return differenceInYears > 1; // Contract is expired if it's older than 1 year
	}

	private void updateEquipeLevel(Equipe equipe, int nbEtudiantsAvecContratsActifs) {
		if (nbEtudiantsAvecContratsActifs >= 3) {
			if (equipe.getNiveau().equals(Niveau.JUNIOR)) {
				equipe.setNiveau(Niveau.SENIOR);
			} else if (equipe.getNiveau().equals(Niveau.SENIOR)) {
				equipe.setNiveau(Niveau.EXPERT);
			}
			equipeRepository.save(equipe);
		}
	}
}