package in.scalive.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.scalive.votezy.dto.VoteRequestDTO;
import in.scalive.votezy.dto.VoteResponseDTO;
import in.scalive.votezy.entity.Vote;
import in.scalive.votezy.service.VotingService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/votes")
public class VotingController {
  private VotingService votingServ;

@Autowired
public VotingController(VotingService votingServ) {
	this.votingServ = votingServ;
}
@PostMapping("/cast")
public ResponseEntity<VoteResponseDTO> castVote(@RequestBody @Valid VoteRequestDTO voteRequest)
 {
	Vote vote=votingServ.castVote(voteRequest.getVoterId(),voteRequest.getCandidateId());
	VoteResponseDTO voteResponse=new VoteResponseDTO("Vote casted successfully.",true,vote.getVoterId(),vote.getCandidateId());
	return new ResponseEntity<VoteResponseDTO>(voteResponse,HttpStatus.CREATED);
 }
@GetMapping()
public ResponseEntity<List<Vote>> getAllVotes()
{
	List<Vote>voteList=votingServ.getAllVotes();
	return new ResponseEntity<>(voteList,HttpStatus.OK);
}
}
