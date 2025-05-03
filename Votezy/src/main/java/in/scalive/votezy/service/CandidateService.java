package in.scalive.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.scalive.votezy.entity.Candidate;
import in.scalive.votezy.entity.Vote;
import in.scalive.votezy.exception.ResourceNotFoundException;
import in.scalive.votezy.repostiory.CandidateRepository;

@Service
public class CandidateService {
private CandidateRepository candidRepo;

@Autowired
public CandidateService(CandidateRepository candidRepo) {
	super();
	this.candidRepo = candidRepo;
}
public Candidate addCandidate(Candidate candidate)
{
	return candidRepo.save(candidate);
}
 public List<Candidate>getAllCandidate()
 {
	 return candidRepo.findAll();
 }
 public Candidate getCandidateById(Long id)
 {
	 Candidate candidate=candidRepo.findById(id).orElse(null);
	 if(candidate==null)
	 {
		 throw new ResourceNotFoundException("Candidate with id:"+id+" not found");
		 
	 }
	 return candidate;
 }
 public Candidate updateCandidate(Long id,Candidate updatedCandidate)
 {
	 Candidate candidate=getCandidateById(id);
	 if(updatedCandidate.getName()!=null)
	 {
		 candidate.setName(updatedCandidate.getName());
	 }
	 if(updatedCandidate.getParty()!=null)
	 {
		 candidate.setParty(updatedCandidate.getParty());
	 }
	 return candidRepo.save(candidate);
 }
 //agar is candidate ko vote nhi mila toh sidha sidha delete ho jaye
 //ager is candidate ko vote mila h toh candidate id null ho jaye vote table mein
 public void deleteCandidate(Long id)
 {
	 Candidate candidate=getCandidateById(id);
	 List<Vote>votes=candidate.getVote();
	 for(Vote v:votes)
	 {
		 v.setCandidate(null);
	 }
	 candidate.getVote().clear();
	 candidRepo.delete(candidate);
 }
}
