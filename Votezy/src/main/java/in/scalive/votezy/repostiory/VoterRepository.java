package in.scalive.votezy.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;

import in.scalive.votezy.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter,Long>{
boolean existsByEmail(String email);
}
