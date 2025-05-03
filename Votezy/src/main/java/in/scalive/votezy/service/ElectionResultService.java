package in.scalive.votezy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.scalive.votezy.entity.Candidate;
import in.scalive.votezy.entity.ElectionResult;
import in.scalive.votezy.exception.ResourceNotFoundException;
import in.scalive.votezy.repostiory.CandidateRepository;
import in.scalive.votezy.repostiory.ElectionResultRepository;
import in.scalive.votezy.repostiory.VoteRepository;

@Service
public class ElectionResultService {
private ElectionResultRepository electionRepo;
private CandidateRepository candidRepo;
private VoteRepository voteRepo;
@Autowired
public ElectionResultService(ElectionResultRepository electionRepo, CandidateRepository candidRepo,
		VoteRepository voteRepo) {
	this.electionRepo = electionRepo;
	this.candidRepo = candidRepo;
	this.voteRepo = voteRepo;
}
public ElectionResult declareElectionResult(String electionName)
{
	Optional<ElectionResult> existingResult=this.electionRepo.findByElectionName(electionName);
	if(existingResult.isPresent())
	{
		return existingResult.get();
	}
	if(voteRepo.count()==0)
	{
		throw new IllegalStateException("Cannot declare the result as no votes have been casted");
	}
	List<Candidate>  allCandidates = candidRepo.findAllByOrderByVoteCountDesc();
    if(allCandidates.isEmpty())
    {
    	throw new ResourceNotFoundException("No candidates availaible");
    }
    Candidate winner=allCandidates.get(0);
    int totalVotes=0;
    for(Candidate candidates:allCandidates)
    {
    	totalVotes+=candidates.getVoteCount();
    }
    ElectionResult result=new ElectionResult();
    result.setElectionName(electionName);
    result.setWinner(winner);
    result.setTotalVotes(totalVotes);
    return electionRepo.save(result);
}
public List<ElectionResult> getAllResults()
{
	return electionRepo.findAll();
}
}
