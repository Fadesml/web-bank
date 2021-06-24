package ru.fadesml.bank.controllers;

import org.springframework.http.HttpStatus;
import ru.fadesml.bank.payload.request.TransferRequest;
import ru.fadesml.bank.models.ETransferType;
import ru.fadesml.bank.payload.response.ServerResponse;
import ru.fadesml.bank.services.ICardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/card")
public class CardController {
    private final ICardService cardService;

    public CardController(ICardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<?> getMyCards(Principal principal) throws JsonProcessingException {
        return cardService.get(principal);
    }

    @PostMapping("/{currency}")
    public ResponseEntity<?> createCard(Principal principal, @PathVariable String currency) throws JsonProcessingException {
        return cardService.create(principal, currency);
    }

    @PostMapping("/rebuild/{number}")
    public ResponseEntity<?> rebuildCard(Principal principal, @PathVariable String number) throws JsonProcessingException {
        return cardService.rebuild(principal, number);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(Principal principal, @RequestBody TransferRequest transferRequest) throws IOException, InterruptedException {
        for (ETransferType value : ETransferType.values()) {
            if (value.name().equals(transferRequest.getType()) && !value.name().equals("CONTRIBUTION_OPEN") && !value.name().equals("CONTRIBUTION_CLOSED")) {
                return cardService.transfer(principal, transferRequest.getCardNumberFrom(), transferRequest.getCardNumberTo(), transferRequest.getAmount(), value);
            }
        }

        return ServerResponse.response(false, "bad_transaction_type", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<?> deleteCard(Principal principal, @PathVariable String number) throws JsonProcessingException {
        return cardService.delete(principal, number);
    }
}
