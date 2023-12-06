package com.kameleoon.controller;

import com.kameleoon.entity.Quote;
import com.kameleoon.entity.Vote;
import com.kameleoon.service.QuoteService;
import com.kameleoon.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/api/vote", produces="application/json")
public class VoteController {

    private final QuoteService quoteService;

    private final VoteService voteService;

    @PostMapping("/{quoteId}/up")
    public ResponseEntity<Quote> voteUp(@PathVariable Long quoteId) {
        Optional<Quote> quoteOptional = quoteService.findById(quoteId);

        if (quoteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Quote quote = quoteOptional.get();
        Quote votedQuote = quoteService.vote(quote, 1);
        return ResponseEntity.ok(votedQuote);
    }

    @PostMapping("/{quoteId}/down")
    public ResponseEntity<Quote> voteDown(@PathVariable Long quoteId) {
        Optional<Quote> quoteOptional = quoteService.findById(quoteId);

        if (quoteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Quote quote = quoteOptional.get();
        Quote votedQuote = quoteService.vote(quote, -1);
        return ResponseEntity.ok(votedQuote);
    }

    @GetMapping("{quoteId}/evolution")
    public ResponseEntity<List<Vote>> evolution(@PathVariable Long quoteId) {
        Optional<Quote> quoteOptional = quoteService.findById(quoteId);

        if (quoteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(voteService.getVoteEvolution(quoteId));
    }
}
