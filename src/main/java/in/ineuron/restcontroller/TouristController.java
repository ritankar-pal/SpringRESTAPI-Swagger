package in.ineuron.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ineuron.model.Tourist;
import in.ineuron.service.ITouristMgmtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/tourist")
@Tag(name = "TouristController")
public class TouristController {

	@Autowired
	private ITouristMgmtService service;


	@PostMapping(value = "/register")
	@Operation(

			summary = "Tourist Registration",
			description = "To Register New Tourists"
			)
	public ResponseEntity<String> enrollTourist(@RequestBody Tourist tourist){

		String resultMsg = service.registerTourist(tourist);
		return new ResponseEntity<>(resultMsg, HttpStatus.OK);
	}


	@GetMapping(value = "/findAll")
	@Operation(
			summary = "GET All Tourists",
			description = "Display All The Tourist Details"
			)
	public ResponseEntity<?> displayTouristDetails(){

		List<Tourist> tourists = service.fetchAllTourists();
		return new ResponseEntity<List<Tourist>>(tourists, HttpStatus.OK);
	}


	@GetMapping(value = "/find/{id}")
	@Operation(

			summary = "GET Tourist By ID",
			description = "To Register New Tourists"
			)
	public ResponseEntity<?> displayTouristById(@PathVariable(value = "id") Integer id){

		Tourist tourist = service.fetToursitById(id);
		return new ResponseEntity<Tourist>(tourist, HttpStatus.OK);

	}

	@PutMapping(value = "/modify")
	@Operation(

			summary = "Update Tourist Details",
			description = "To Register New Tourists"
			)
	public ResponseEntity<String> updateTourist(@RequestBody Tourist tourist){

		String result = service.updateTouristByDetails(tourist);
		return new ResponseEntity<String>(result, HttpStatus.OK);

	}


	@PatchMapping(value = "/budgetModify/{id}/{hike}")
	@Operation(

			summary = "Update Tourist Budget",
			description = "To Register New Tourists"
			)
	public ResponseEntity<String> modifyTouristBudgetById(@PathVariable(value = "id") Integer id, @PathVariable("hike") Float hike){

		String result = service.updateTouristBudgetById(id, hike);
		return new ResponseEntity<String>(result, HttpStatus.OK);

	}


	@DeleteMapping(value = "/delete/{id}")
	@Operation(

			summary = "DELETE Tourist",
			description = "To Register New Tourists"
			)
	public ResponseEntity<String> deleteTouristByID(@PathVariable(value = "id") Integer id){

		String result = service.deleteTouristById(id);
		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

}





