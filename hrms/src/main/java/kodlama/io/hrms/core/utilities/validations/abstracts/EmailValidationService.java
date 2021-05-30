package kodlama.io.hrms.core.utilities.validations.abstracts;

import kodlama.io.hrms.entities.concretes.Users;

public interface EmailValidationService {
	public boolean sendEmail(Users user);
}
