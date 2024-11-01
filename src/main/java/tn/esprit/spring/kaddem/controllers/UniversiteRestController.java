package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.dto.UniversityDTO;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.services.IUniversiteService;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {

	IUniversiteService universiteService;

	@GetMapping("/retrieve-all-universites")
	public List<Universite> getUniversites() {
		return universiteService.retrieveAllUniversites();

	}

	@GetMapping("/retrieve-universite/{universite-id}")
	public Universite retrieveUniversite(@PathVariable("universite-id") Integer universiteId) {
		return universiteService.retrieveUniversite(universiteId);
	}

	@PostMapping("/add-universite")
	public UniversityDTO addUniversite(@RequestBody UniversityDTO uDTO) {
		Universite universite = new Universite();
		universite.setDepartements(uDTO.getDepartements());
		universite.setNomUniv(uDTO.getNomUniv());
		Universite savedUniverite = universiteService.addUniversite(universite);

		UniversityDTO responseDTO = new UniversityDTO();

		responseDTO.setNomUniv(savedUniverite.getNomUniv());
		responseDTO.setDepartements(savedUniverite.getDepartements());
		return responseDTO;

	}

	@DeleteMapping("/remove-universite/{universite-id}")
	public void removeUniversite(@PathVariable("universite-id") Integer universiteId) {
		universiteService.deleteUniversite(universiteId);
	}

	@PutMapping("/update-universite")
	public UniversityDTO updateEtudiant(@RequestBody UniversityDTO universityDTO) {
		Universite universite = new Universite();
		universite.setDepartements(universityDTO.getDepartements());
		universite.setNomUniv(universityDTO.getNomUniv());
		Universite savedUniverite = universiteService.updateUniversite(universite);

		UniversityDTO responseDTO = new UniversityDTO();

		responseDTO.setNomUniv(savedUniverite.getNomUniv());
		responseDTO.setDepartements(savedUniverite.getDepartements());
		return responseDTO;
	}


	@PutMapping(value="/affecter-universite-departement/{universiteId}/{departementId}")
	public void affectertUniversiteToDepartement(@PathVariable("universiteId") Integer universiteId, @PathVariable("departementId")Integer departementId){
		universiteService.assignUniversiteToDepartement(universiteId, departementId);
	}

	@GetMapping(value = "/listerDepartementsUniversite/{idUniversite}")
	public Set<Departement> listerDepartementsUniversite(@PathVariable("idUniversite") Integer idUniversite) {

		return universiteService.retrieveDepartementsByUniversite(idUniversite);
	}

}


