package kodlama.io.hrms.core.utilities.validations.concretes;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import kodlama.io.hrms.core.utilities.validations.abstracts.FakeEmailCheckService;

@Service
public class FakeEmailCheckManager implements FakeEmailCheckService{

	private static final String EMAIL_PATTERN = "^[A-Z0-9.%+-]+@[A-Z0-9.-]+.(com|org|net|edu|gov|mil|biz|info|mobi)(.[A-Z]{2})?$";

    @Override
    public boolean checkIfRealEmail(String email) {

            Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);
            return pattern.matcher(email).find();

    }

}
