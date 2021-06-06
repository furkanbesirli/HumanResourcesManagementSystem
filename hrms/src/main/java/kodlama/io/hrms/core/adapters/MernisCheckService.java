package kodlama.io.hrms.core.adapters;

import kodlama.io.hrms.entities.concretes.Candidates;

public interface MernisCheckService {

	boolean checkIfRealPerson(Candidates candidate);
}