package kodlama.io.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlama.io.hrms.business.abstracts.EmployersService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.ErrorResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessResult;
import kodlama.io.hrms.core.utilities.validations.abstracts.EmailValidationService;
import kodlama.io.hrms.core.utilities.validations.abstracts.EmployeesValidationService;
import kodlama.io.hrms.core.utilities.validations.abstracts.FakeEmailCheckService;
import kodlama.io.hrms.dataAccess.abstracts.EmployersDao;
import kodlama.io.hrms.entities.concretes.Employers;

@Service
public class EmployersManager implements EmployersService{

	private EmployersDao employersDao;
	private EmailValidationService emailValidationService;
	private FakeEmailCheckService fakeEmailCheckService;
	private EmployeesValidationService employeesValidationService;
	
	@Autowired
	public EmployersManager(EmployersDao employersDao, EmailValidationService emailValidationService,
			FakeEmailCheckService fakeEmailCheckService, EmployeesValidationService employeesValidationService) {
		super();
		this.employersDao = employersDao;
		this.emailValidationService = emailValidationService;
		this.fakeEmailCheckService = fakeEmailCheckService;
		this.employeesValidationService = employeesValidationService;
	}

	@Override
	public DataResult<List<Employers>> getAll() {		
		return new SuccessDataResult<List<Employers>>(this.employersDao.findAll(), "Is verenler listelendi.");
	}

	@Override
	public Result add(Employers employer) {
		if(		employer.getCompanyName().isBlank()
                ||employer.getEmail().isBlank()
                ||employer.getPassword().isBlank()
                ||employer.getPhoneNumber().isBlank()
                ||employer.getWebAddress().isBlank()) {
           return new ErrorResult("Hicbir alan bos birakilamaz!");
           
		}else if(!employer.getWebAddress().contains(employer.getEmail().split("@")[0])) {
			return new ErrorResult("Domain le mail eslesmiyor.");
		}else if(!fakeEmailCheckService.checkIfRealEmail(employer.getEmail())) {
			return new ErrorResult("Email dogru girilmedi.");
		}else if(employersDao.findByEmailEquals(employer.getEmail()) != null) {
			return new ErrorResult("Email sistemde kayitli.");
		}else if(!employer.getPassword().equals(employer.getEmployersPasswordRepeat())) {
			return new ErrorResult("Sifreler eslesmiyor.");
		}else {
			if(emailValidationService.sendEmail(employer) || employeesValidationService.isValid(employer)) {
				this.employersDao.save(employer);
				return new SuccessResult("Kayit basarili.");
			}else {
				return new ErrorResult("Dogrulama basarisiz.");
			}
			
		}
	}
	
	
	
}
