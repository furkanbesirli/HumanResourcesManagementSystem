package kodlama.io.hrms.business.concretes;

import java.util.List;


import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.CandidatesService;
import kodlama.io.hrms.core.adapters.FakeMernisCheckService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.ErrorResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.core.utilities.validations.abstracts.EmailValidationService;
import kodlama.io.hrms.core.utilities.validations.abstracts.FakeEmailCheckService;
import kodlama.io.hrms.dataAccess.abstracts.CandidatesDao;
import kodlama.io.hrms.entities.concretes.Candidates;


@Service
public class CandidatesManager implements CandidatesService{

	private CandidatesDao candidatesDao;
	private FakeMernisCheckService fakeMernisCheckService;
	private EmailValidationService emailValidationService;
	private FakeEmailCheckService fakeEmailCheckService;

	
	public CandidatesManager(CandidatesDao candidatesDao, FakeMernisCheckService fakeMernisCheckService, EmailValidationService emailValidationService,
			FakeEmailCheckService fakeEmailCheckService) {
		super();
		this.candidatesDao = candidatesDao;
		this.fakeMernisCheckService = fakeMernisCheckService;
		this.emailValidationService = emailValidationService;
		this.fakeEmailCheckService = fakeEmailCheckService;
	}

	@Override
	public DataResult<List<Candidates>> getAll() {
		
		return new SuccessDataResult<List<Candidates>>(this.candidatesDao.findAll(), "Data listelendi");
	}

	@Override
	public Result add(Candidates candidate) {
		if(		   candidate.getFirstName().isBlank()
                || candidate.getLastName().isBlank()
                || candidate.getBirthDate() == null
                || candidate.getEmail().isBlank()
                || candidate.getPassword().isBlank()
                || candidate.getNationalityId().isBlank()) {
           return new ErrorResult("Hicbir alan bos birakilamaz!");

       }else if(!fakeMernisCheckService.checkIfRealPerson(candidate)) {
           return new ErrorResult("Mernis bilgileri dogrulanamadi!");

       }else if(!fakeEmailCheckService.checkIfRealEmail(candidate.getEmail())) {
           return new ErrorResult("Email formati dogrulanamadi!");

       }else if(!candidate.getPassword().equals(candidate.getPasswordRepeat())) {
    	   return new ErrorResult("Sifreler uyumsuz. Tekrar deneyiniz.");
    	   
       }else if(candidatesDao.findByEmailEquals(candidate.getEmail()) != null) {
           return new ErrorResult("Bu email zaten sisteme kayitli!");

       }else if(candidatesDao.findByNationalityIdEquals(candidate.getNationalityId()) != null) {
           return new ErrorResult("Bu Tc No zaten sisteme kayitli!");

       }else {
    	   
    	   if(emailValidationService.sendEmail(candidate)) {
    		   this.candidatesDao.save(candidate);
    		   return new SuccessResult("Tum bilgiler basariyla dogrulandi.");
    	   }else
    		   return new ErrorResult("Eposta dogrulamasi basarisiz. Tekrar deneyiniz.");

       }
		
	}



	
	
}

