package com.localnews.localnews.controllers.pollControllers;

import com.localnews.localnews.models.BooleanResponseModel;
import com.localnews.localnews.models.PollExceptions.GenericErrorCreatePoll;
import com.localnews.localnews.models.PollModels.PollModel;
import com.localnews.localnews.services.pollServices.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/polls")
public class PollController {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(PollController.class);

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
                    "Erro no formato da requisição."));
        }
        catch (RuntimeException e) {
            //logger.error("erro: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BooleanResponseModel(false,
                    "Erro interno."));
        }
    }

    @GetMapping("/info")
    public ResponseEntity<?> getAllPolls() {
        try {
            return ResponseEntity.ok(pollService.getAllPolls());
        }
        catch (GenericErrorCreatePoll e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BooleanResponseModel(false,
                    "Erro no formato da requisição."));
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BooleanResponseModel(false,
                    "Erro interno."));
        }
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getPollById(@PathVariable Long id) {
        try {
            Optional<PollModel> poll = Optional.ofNullable(pollService.infoPoll(id));

            if (poll.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BooleanResponseModel(false,
                        "Enquete não encontrada."));
            }
            return ResponseEntity.ok(pollService.infoPoll(id));
        }
        catch (GenericErrorCreatePoll e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BooleanResponseModel(false,
                    "Erro no formato da requisição."));
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new BooleanResponseModel(false,
                    "Erro interno."));
        }
    }
}