package com.localnews.localnews.services.pollServices;

import com.localnews.localnews.models.BooleanResponseModel;
import com.localnews.localnews.models.PollExceptions.GenericErrorCreatePoll;
import com.localnews.localnews.models.PollExceptions.GenericErrorRegisterVote;
import com.localnews.localnews.models.PollModels.PollOption;
import com.localnews.localnews.repositories.PollRepositories.PollOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollOptionService {

    @Autowired
    private PollOptionRepository pollOptionRepository;

    public PollOption vote(Long pollId, Long optionId) {
        PollOption option = pollOptionRepository.findById(optionId)
                .orElseThrow(() -> new GenericErrorCreatePoll("Opção não encontrada."));

        if (!option.getPoll().getId().equals(pollId)) {
            throw new GenericErrorCreatePoll("Enquete não existe.");
        }
        option.setVotes(option.getVotes() + 1);
        return pollOptionRepository.save(option);
    }

}
