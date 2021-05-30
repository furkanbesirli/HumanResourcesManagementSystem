package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.JobPositionsService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.ErrorResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.dataAccess.abstracts.JobPositionsDao;
import kodlama.io.hrms.entities.concretes.JobPositions;


@Service
public class JobPositionsManager implements JobPositionsService{

	private JobPositionsDao jobPositionsDao;

	@Autowired
	public JobPositionsManager(JobPositionsDao jobPositionsDao) {
		super();
		this.jobPositionsDao = jobPositionsDao;
	}


	@Override
	public DataResult<List<JobPositions>> getAll() {

		return new SuccessDataResult<List<JobPositions>>(this.jobPositionsDao.findAll()
				,"Is pozisyonlari listelendi");
	}

	@Override
	public Result add(JobPositions jobPositions) {

		if(jobPositionsDao.findByPositionNameEquals(jobPositions.getPositionName()) != null) {
            return new ErrorResult("Bu is pozisyonu zaten sisteme kayitli!");

        }else {
            this.jobPositionsDao.save(jobPositions);
        return new SuccessResult("İs pozisyonu basariyla eklendi!");
        }
	}
	
}
