package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.dto.DepartementDTO;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/departement")
public class DepartementRestController {
	IDepartementService departementService;
	@GetMapping("/retrieve-all-departements")
	public List<Departement> getDepartements() {
		return departementService.retrieveAllDepartements();
	}
	@GetMapping("/retrieve-departement/{departement-id}")
	public Departement retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
		return departementService.retrieveDepartement(departementId);
	}

	@PostMapping("/add-departement")
	public DepartementDTO addDepartement(@RequestBody DepartementDTO departementDTO) {
		Departement departement=new Departement();
		departement.setNomDepart(departementDTO.getNomDepart());
		Departement savedDepartement = departementService.addDepartement(departement);
		departementDTO.setNomDepart(savedDepartement.getNomDepart());
		return departementDTO;
	}

	@DeleteMapping("/remove-departement/{departement-id}")
	public void removeDepartement(@PathVariable("departement-id") Integer departementId) {
		departementService.deleteDepartement(departementId);
	}

	@PutMapping("/update-departement/{idDepart}")
	public DepartementDTO updateDepartement(@PathVariable("idDepart") Integer idDepart,
											@RequestBody DepartementDTO departementDTO) {
		Departement departement = new Departement();
		departement.setIdDepart(idDepart);
		departement.setNomDepart(departementDTO.getNomDepart());

		Departement updatedDepartement = departementService.updateDepartement(departement);

		DepartementDTO updatedDepartementDTO = new DepartementDTO();
		updatedDepartementDTO.setNomDepart(updatedDepartement.getNomDepart());

		return updatedDepartementDTO;
	}

}


