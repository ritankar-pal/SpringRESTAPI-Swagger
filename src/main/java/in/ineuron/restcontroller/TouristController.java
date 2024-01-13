package in.ineuron.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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


@RestController
@RequestMapping(value = "/api/tourist")
public class TouristController {

	@Autowired
	private ITouristMgmtService service;


	@PostMapping(value = "/register")
	public ResponseEntity<String> enrollTourist(@RequestBody Tourist tourist){

		try {
			String resultMsg = service.registerTourist(tourist);
			return new ResponseEntity<>(resultMsg, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("Problem Occured in Tourist Enrollment", HttpStatus.INTERNAL_SERVER_ERROR);	
		}

	}


	@GetMapping(value = "/findAll")
	public ResponseEntity<?> displayTouristDetails(){

		try {

			List<Tourist> tourists = service.fetchAllTourists();
			return new ResponseEntity<List<Tourist>>(tourists, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("Problem in Fetching Tourist Details", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@GetMapping(value = "/find/{id}")
	public ResponseEntity<?> displayTouristById(@PathVariable(value = "id") Integer id){


		try {
			Tourist tourist = service.fetToursitById(id);
			return new ResponseEntity<Tourist>(tourist, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage() , HttpStatus.NOT_FOUND);
		}

	}
	
	@PutMapping(value = "/modify")
	public ResponseEntity<String> updateTourist(@RequestBody Tourist tourist){
		
		try {
			String result = service.updateTouristByDetails(tourist);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
				
	}
	
	
	@PatchMapping(value = "/budgetModify/{id}/{hike}")
	public ResponseEntity<String> modifyTouristBudgetById(@PathVariable(value = "id") Integer id, @PathVariable("hike") Float hike){
		
		try {
			String result = service.updateTouristBudgetById(id, hike);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
				
	}
	
}





