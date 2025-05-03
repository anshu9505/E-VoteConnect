package in.scalive.votezy.repostiory;

import org.springframework.data.jpa.repository.JpaRepository;

import in.scalive.votezy.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote,Long>{

}
