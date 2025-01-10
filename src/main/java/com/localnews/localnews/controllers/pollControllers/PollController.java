package com.localnews.localnews.controllers.pollControllers;

import com.localnews.localnews.models.BooleanResponseModel;
import com.localnews.localnews.models.PollExceptions.GenericErrorCreatePoll;
import com.localnews.localnews.models.PollModels.PollModel;
import com.localnews.localnews.services.pollServices.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @PostMapping("/create")
    public ResponseEntity<?> createPoll(@RequestBody PollModel poll) {
        try {
            pollService.createPoll(poll);
            return ResponseEntity.status(HttpStatus.CREATED).body(new BooleanResponseModel(true,
                    "Enquete registrada com sucesso."));
        }
        catch (GenericErrorCreatePoll e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BooleanResponseModel(false,
                    e.getMessage()));
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BooleanResponseModel(false,
                    "Erro interno."));
        }
    }
}