package kodlama.io.hrms.core.utilities.validations.concretes;

import org.springframework.stereotype.Service;

import kodlama.io.hrms.core.utilities.validations.abstracts.EmployeesValidationService;
import kodlama.io.hrms.entities.concretes.Users;

@Service
public class EmployeesValidationManager implements EmployeesValidationService{

	@Override
	public boolean isValid(Users user) {
		
		return true;
	}

}
