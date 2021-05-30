package kodlama.io.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms.entities.concretes.Candidates;


public interface CandidatesDao extends JpaRepository<Candidates, Integer>{
	
	Candidates findByNationalityIdEquals(String nationalityId);

    Candidates findByEmailEquals(String email);
	
}
