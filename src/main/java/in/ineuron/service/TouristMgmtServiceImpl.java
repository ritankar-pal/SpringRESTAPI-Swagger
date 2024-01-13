package in.ineuron.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ineuron.dao.ITouristRepo;
import in.ineuron.exception.TouristNotFoundException;
import in.ineuron.model.Tourist;

@Service
public class TouristMgmtServiceImpl implements ITouristMgmtService{

	@Autowired
	private ITouristRepo repo;
	
	
	@Override
	public String registerTourist(Tourist tourist) {
		Integer tID = repo.save(tourist).getTid();
		return "Tourist Is Registered Having the TicketID: " + tID;
	}


	@Override
	public List<Tourist> fetchAllTourists() {
		
		List<Tourist> tourists = repo.findAll();
		tourists.sort((t1, t2) -> t1.getTid().compareTo(t2.getTid()));
		
		return tourists;
	}


	@Override
	public Tourist fetToursitById(Integer id) {
		
		Optional<Tourist> optional = repo.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new TouristNotFoundException("Tourist with Id: " + id + " not found.");
	}


	@Override
	public String updateTouristByDetails(Tourist tourist) {
		
		Optional<Tourist> optional = repo.findById(tourist.getTid());
		
		if(optional.isPresent()) {
			repo.save(tourist);
			return "Toursist With the ID: " + tourist.getTid() + " updated";
		}
		
		throw new TouristNotFoundException("Tourist with Id: " + tourist.getTid() + " not found.");
	}


	@Override
	public String updateTouristBudgetById(Integer id, Float hikePercent) {
		
		Optional<Tourist> optional = repo.findById(id);		
		
		if(optional.isPresent()) {
			
			Tourist tourist = optional.get();
			System.out.println(tourist);
				
			Double budget = tourist.getBudget();;
			budget = budget + (budget * (hikePercent / 100));
			
			tourist.setBudget(budget);
			
			repo.save(tourist);
			
			return "Tourist with ID: " + id + ", budget Updated. The new Budget is: " + tourist.getBudget() ;
			
		}
		
		throw  new TouristNotFoundException("Tourist with the id: " + id + " not found");
	}

}






