package kodlama.io.hrms.core.adapters;

import org.springframework.stereotype.Service;

import kodlama.io.hrms.entities.concretes.Candidates;

@Service
public class FakeMernisCheckManager implements FakeMernisCheckService{

	@Override
	public boolean checkIfRealPerson(Candidates candidates) {
		if(candidates.getFirstName().length() < 2) {
			System.out.println("Isim 2 karakterden kisa olaamz.");
			return false;
		}else if(candidates.getLastName().length() < 2) {
			System.out.println("Soyisim 2 karakterden kisa olaamz.");
			return false;
		}else if(candidates.getNationalityId().length() != 11) {
			System.out.println("TC NO 11 haneli olmalidir.");
			return false;
		}else
			return true;
		
	}
	
	
	
}
