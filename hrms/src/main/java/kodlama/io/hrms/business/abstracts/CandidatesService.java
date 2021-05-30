package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.Candidates;


public interface CandidatesService {
	
	DataResult<List<Candidates>> getAll();
	
	Result add(Candidates candidate);
	

	
}
