package in.scalive.votezy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteRequestDTO {
    @NotNull(message="VoterId is Required")
	Long voterId;
    @NotNull(message="CandidateId is Required")
    Long candidateId;
}
