package kodlama.io.hrms.business.abstracts;

import java.util.List;

import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.JobPositions;

public interface JobPositionsService {
	
	DataResult<List<JobPositions>> getAll();
	
	Result add(JobPositions jobPositions);
	
	
}
