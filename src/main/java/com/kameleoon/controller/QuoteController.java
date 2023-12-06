package com.kameleoon.controller;

import com.kameleoon.entity.Quote;
import com.kameleoon.entity.User;
import com.kameleoon.service.QuoteService;
import com.kameleoon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/api/quotes", produces="application/json")
public class QuoteController {

    private final QuoteService quoteService;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Quote> save(@RequestBody Quote newQuote, @RequestParam Long userId) {
        Optional<User> userOptional = userService.findById(userId);

        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();
        return new ResponseEntity<>(quoteService.save(newQuote, user), HttpStatus.CREATED);
    }

    @PutMapping("/update/{quoteId}")
    public ResponseEntity<Quote> update(@PathVariable Long quoteId, @RequestBody Quote updatedQuote) {
        Optional<Quote> quoteOptional = quoteService.findById(quoteId);

        if (quoteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Quote quote = quoteOptional.get();
        quote.setContent(updatedQuote.getContent());
        return ResponseEntity.ok(quoteService.save(quote, quote.getAuthor()));
    }

    @DeleteMapping("/delete/{quoteId}")
    public ResponseEntity<Void> delete(@PathVariable Long quoteId) {
        Optional<Quote> quoteOptional = quoteService.findById(quoteId);

        if (quoteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        quoteService.delete(quoteId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Quote>> findAll() {
        return ResponseEntity.ok(quoteService.findAll());
    }

    @GetMapping("/random")
    public ResponseEntity<Quote> getRandom() {
        return ResponseEntity.ok(quoteService.getRandom());
    }

    @GetMapping("/top")
    public ResponseEntity<List<Quote>> getTopQuotes() {
        List<Quote> topQuotes = quoteService.getTopQuotes(10);
        return ResponseEntity.ok(topQuotes);
    }

    @GetMapping("/worst")
    public ResponseEntity<List<Quote>> getWorstQuotes() {
        List<Quote> worstQuotes = quoteService.getWorstQuotes(10);
        return ResponseEntity.ok(worstQuotes);
    }

    @GetMapping("/{quoteId}")
    public ResponseEntity<Quote> getQuote(@PathVariable Long quoteId) {
        Optional<Quote> quoteOptional = quoteService.findById(quoteId);
        return quoteOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
