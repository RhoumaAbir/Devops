package tn.esprit.spring.kaddem.controllers;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.dto.EquipeDTO;
import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.services.IEquipeService;
import java.util.List;

@RestController
@RequestMapping("/equipe")
public class EquipeRestController {

	public EquipeRestController(IEquipeService equipeService) {
		this.equipeService = equipeService;
	}

	IEquipeService equipeService;

	@GetMapping("/retrieve-all-equipes")
	public List<Equipe> getEquipes() {
		return equipeService.retrieveAllEquipes();
	}

	@GetMapping("/retrieve-equipe/{equipe-id}")
	public Equipe retrieveEquipe(@PathVariable("equipe-id") Integer equipeId) {
		return equipeService.retrieveEquipe(equipeId);
	}

	@PostMapping("/add-equipe")
	public EquipeDTO addEquipe(@RequestBody EquipeDTO equipeDTO) {
		Equipe equipe = new Equipe();
		equipe.setNomEquipe(equipeDTO.getNomEquipe());
		equipe.setNiveau(equipeDTO.getNiveau());

		Equipe savedEquipe = equipeService.addEquipe(equipe);
		EquipeDTO responseDTO = new EquipeDTO();

		responseDTO.setNomEquipe(savedEquipe.getNomEquipe());
		responseDTO.setNiveau(savedEquipe.getNiveau());

		return responseDTO;
	}

	@DeleteMapping("/remove-equipe/{equipe-id}")
	public void removeEquipe(@PathVariable("equipe-id") Integer equipeId) {
		equipeService.deleteEquipe(equipeId);
	}

	@PutMapping("/update-equipe")
	public EquipeDTO updateEtudiant(@RequestBody EquipeDTO equipeDTO) {
		Equipe equipe = new Equipe();
		equipe.setNomEquipe(equipeDTO.getNomEquipe());
		equipe.setNiveau(equipeDTO.getNiveau());

		Equipe savedEquipe = equipeService.updateEquipe(equipe);
		EquipeDTO responseDTO = new EquipeDTO();

		responseDTO.setNomEquipe(savedEquipe.getNomEquipe());
		responseDTO.setNiveau(savedEquipe.getNiveau());

		return responseDTO;
	}

	@Scheduled(cron="0 0 13 * * *")
	@PutMapping("/faireEvoluerEquipes")
	public void faireEvoluerEquipes() {
		 equipeService.evoluerEquipes() ;
	}
}