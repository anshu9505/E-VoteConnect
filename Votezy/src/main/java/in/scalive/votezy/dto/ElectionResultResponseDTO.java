package in.scalive.votezy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ElectionResultResponseDTO {
private String electionName;
private int totalVotes;
private long winnerId;
private int winnerVotes;
}
