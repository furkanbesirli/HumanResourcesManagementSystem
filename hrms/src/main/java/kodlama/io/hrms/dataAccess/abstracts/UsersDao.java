package kodlama.io.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlama.io.hrms.entities.concretes.JobPositions;

public interface UsersDao extends JpaRepository<JobPositions, Integer>{
	
	
}
