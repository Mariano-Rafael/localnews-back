package com.localnews.localnews.services.pollServices;

import com.localnews.localnews.models.PollExceptions.GenericErrorCreatePoll;
import com.localnews.localnews.models.PollModels.PollModel;
import com.localnews.localnews.models.PollModels.PollOption;
import com.localnews.localnews.repositories.pollRepositories.PollOptionRepository;
import com.localnews.localnews.repositories.pollRepositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PollService {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PollService.class);

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private PollOptionRepository pollOptionRepository;

    @Transactional
    public void createPoll(PollModel pollModel) {
       // logger.info("Iniciando PollService.createPoll() com PollModel: {}", pollModel);
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
