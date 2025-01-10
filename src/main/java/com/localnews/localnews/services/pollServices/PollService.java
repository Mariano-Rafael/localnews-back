package com.localnews.localnews.services.pollServices;

import com.localnews.localnews.models.PollExceptions.GenericErrorCreatePoll;
import com.localnews.localnews.models.PollModels.PollModel;
import com.localnews.localnews.models.PollModels.PollOption;
import com.localnews.localnews.repositories.PollRepositories.PollOptionRepository;
import com.localnews.localnews.repositories.PollRepositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private PollOptionRepository pollOptionRepository;

    public PollModel createPoll(PollModel pollModel) {
        if (pollModel.getQuestion() == null || pollModel.getQuestion().isEmpty()) {
            throw new GenericErrorCreatePoll("Enquete não pode ser criada sem pergunta");
        }
        if (pollModel.getOptions() == null || pollModel.getOptions().isEmpty()) {
            throw new GenericErrorCreatePoll("Enquete não pode ser criada sem opções");
        }
        for (PollOption option : pollModel.getOptions()) {
            option.setPoll(pollModel);
        }
        return pollRepository.save(pollModel);
    }
}
