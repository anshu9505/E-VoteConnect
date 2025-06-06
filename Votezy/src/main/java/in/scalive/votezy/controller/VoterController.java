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

import in.scalive.votezy.entity.Voter;
import in.scalive.votezy.service.VoterService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class VoterController {
private VoterService voterServ;
@Autowired
public VoterController(VoterService voterServ) {
	this.voterServ = voterServ;
}
@PostMapping("/register")
public ResponseEntity<Voter>registerVoter(@RequestBody @Valid Voter voter)
{
	Voter savedVoter=voterServ.registerVoter(voter);
	return new ResponseEntity<>(savedVoter,HttpStatus.CREATED);
}
@GetMapping("/{id}")
public ResponseEntity<Voter>getVoterById(@PathVariable @Valid Long id)
{
	Voter savedVoter=voterServ.getVoterById(id);
	return new ResponseEntity<>(savedVoter,HttpStatus.OK);
}
@GetMapping()
public ResponseEntity<List<Voter>>getAllVoter()
{
	List<Voter>voterList=voterServ.getAllVoters();
	return new ResponseEntity<>(voterList,HttpStatus.OK);
}
@PutMapping("/update/{id}")
public ResponseEntity<Voter>updateVoter(@PathVariable Long id,@RequestBody Voter voter)
{
	Voter updatedVoter=voterServ.updateVoter(id, voter);
	return new ResponseEntity<>(updatedVoter,HttpStatus.OK);
}
@DeleteMapping("/delete/{id}")
public ResponseEntity<String>deleteVoter(@PathVariable Long id)
{
	voterServ.deleteVoter(id);
	return new ResponseEntity<>("Voter with id :"+id+" deleted successfully.",HttpStatus.OK);
}
}
