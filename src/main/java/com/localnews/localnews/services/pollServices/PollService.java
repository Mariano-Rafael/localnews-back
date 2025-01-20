package com.localnews.localnews.services.pollServices;

import com.localnews.localnews.models.PollExceptions.GenericErrorCreatePoll;
import com.localnews.localnews.models.PollModels.PollModel;
import com.localnews.localnews.models.PollModels.PollOption;
import com.localnews.localnews.repositories.pollRepositories.PollOptionRepository;
import com.localnews.localnews.repositories.pollRepositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private PollOptionRepository pollOptionRepository;

    public void createPoll(PollModel pollModel) {
        if (pollModel.getQuestion() == null || pollModel.getQuestion().isEmpty()) {
            throw new GenericErrorCreatePoll("Enquete não pode ser criada sem pergunta");
        }
        if (pollModel.getOptions() == null || pollModel.getOptions().isEmpty()) {
            throw new GenericErrorCreatePoll("Enquete não pode ser criada sem opções");
        }
        for (PollOption option : pollModel.getOptions()) {
            option.setPoll(pollModel);
        }
        pollRepository.save(pollModel);
    }

    public List<PollModel> getAllPolls() {
        return pollRepository.findAll();
    }

    public PollModel infoPoll(Long id) {
        return pollRepository.findById(id).orElse(null);
    }
}
