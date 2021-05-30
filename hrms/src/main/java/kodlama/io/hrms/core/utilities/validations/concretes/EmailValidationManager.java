package kodlama.io.hrms.core.utilities.validations.concretes;

import org.springframework.stereotype.Service;

import kodlama.io.hrms.core.utilities.validations.abstracts.EmailValidationService;
import kodlama.io.hrms.entities.concretes.Users;

@Service
public class EmailValidationManager implements EmailValidationService{

	@Override
	public boolean sendEmail(Users user) {

		return true;
	}

}
