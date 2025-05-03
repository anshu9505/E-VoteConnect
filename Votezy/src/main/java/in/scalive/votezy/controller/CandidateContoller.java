package in.scalive.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.scalive.votezy.entity.Candidate;
import in.scalive.votezy.service.CandidateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin
public class CandidateContoller {
private CandidateService candidServ;

@Autowired
public CandidateContoller(CandidateService candidServ) {
	this.candidServ = candidServ;
}
@PostMapping("/add")
public ResponseEntity<Candidate> addCandidate(@RequestBody @Valid Candidate candidate)
{
	Candidate savedCandidate=candidServ.addCandidate(candidate);
	return new ResponseEntity<Candidate>(savedCandidate,HttpStatus.CREATED);
}
@GetMapping
public ResponseEntity<List<Candidate>> getAllCandidate()
{
	List<Candidate> candidateList=candidServ.getAllCandidate();
	return new ResponseEntity<List<Candidate>>(candidateList,HttpStatus.OK);
}
@GetMapping("/{id}")
public ResponseEntity<Candidate> getCandidateById(@PathVariable("id") Long id)
{
	Candidate candidate=candidServ.getCandidateById(id);
	return new ResponseEntity<Candidate>(candidate,HttpStatus.OK);
}
@PutMapping("/update/{id}")
public ResponseEntity<Candidate> updateCandidate(@PathVariable("id") Long id,@RequestBody Candidate updatedCandidate)
{
	Candidate candidate=candidServ.updateCandidate(id, updatedCandidate);
	return new ResponseEntity<Candidate>(candidate,HttpStatus.OK);
}
@DeleteMapping("/delete/{id}")
public ResponseEntity<String> updateCandidate(@PathVariable("id") Long id)
{
    candidServ.deleteCandidate(id);
	return new ResponseEntity<String>("Candidate with id:"+id+" deleted successfully.",HttpStatus.OK);
}
}
