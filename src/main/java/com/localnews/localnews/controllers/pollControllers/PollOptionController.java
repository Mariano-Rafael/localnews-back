package com.localnews.localnews.controllers.pollControllers;

import com.localnews.localnews.models.BooleanResponseModel;
import com.localnews.localnews.models.PollExceptions.GenericErrorCreatePoll;
import com.localnews.localnews.models.PollModels.PollOption;
import com.localnews.localnews.services.pollServices.PollOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poll-option")
public class PollOptionController {

    @Autowired
    private PollOptionService pollOptionService;

    @PostMapping("/vote/{pollId}/{optionId}")
    public ResponseEntity<?> vote(@PathVariable Long pollId, @PathVariable Long optionId) {
        try {
            PollOption updateOption = pollOptionService.vote(pollId, optionId);
            return ResponseEntity.status(HttpStatus.OK).body(updateOption);
        }
        catch (GenericErrorCreatePoll e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BooleanResponseModel(false,
                    e.getMessage()));
        }
    }
}
