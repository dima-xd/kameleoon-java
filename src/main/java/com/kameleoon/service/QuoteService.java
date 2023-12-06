package com.kameleoon.service;

import com.kameleoon.entity.Quote;
import com.kameleoon.entity.User;

import java.util.List;
import java.util.Optional;

public interface QuoteService {
    Quote save(Quote newQuote, User user);

    List<Quote> findAll();

    Optional<Quote> findById(Long quoteId);

    Quote getRandom();

    List<Quote> getTopQuotes(int count);

    List<Quote> getWorstQuotes(int count);

    void delete(Long quoteId);

    Quote vote(Quote quote, int voteValue);
}
