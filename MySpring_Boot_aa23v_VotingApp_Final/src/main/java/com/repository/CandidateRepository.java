package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer>{
	
	
	@Query("select votes from Candidate where candidate = :candidate")
	public int getNumOfVotes(@Param("candidate") String candidate);

	
	
	@Query("select c from Candidate c where c.candidate = :candidate")
	public Candidate getCandidateByCandidate(@Param("candidate") String candidate);
	
	
}
