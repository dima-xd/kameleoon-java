package com.kameleoon.service.impl;

import com.kameleoon.entity.Quote;
import com.kameleoon.entity.User;
import com.kameleoon.entity.Vote;
import com.kameleoon.repository.QuoteRepository;
import com.kameleoon.service.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;

    @Override
    public Quote save(Quote newQuote, User user) {
        newQuote.setAuthor(user);
        return quoteRepository.save(newQuote);
    }

    @Override
    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    @Override
    public Optional<Quote> findById(Long quoteId) {
        return quoteRepository.findById(quoteId);
    }

    @Override
    public Quote getRandom() {
        return quoteRepository.findRandom();
    }

    @Override
    public List<Quote> getTopQuotes(int count) {
        Pageable pageable = PageRequest.of(0, count, Sort.by(Sort.Order.desc("votes")));
        return quoteRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Quote> getWorstQuotes(int count) {
        Pageable pageable = PageRequest.of(0, count, Sort.by(Sort.Order.asc("votes")));
        return quoteRepository.findAll(pageable).getContent();
    }

    @Override
    public void delete(Long quoteId) {
        quoteRepository.deleteById(quoteId);
    }

    @Override
    public Quote vote(Quote quote, int voteValue) {
        Vote vote = new Vote();
        vote.setVoteValue(voteValue);
        vote.setQuote(quote);
        vote.setUser(quote.getAuthor());

        if (quote.getVotes() == null) {
            quote.setVotes(new ArrayList<>());
        }

        quote.getVotes().add(vote);
        return save(quote, quote.getAuthor());
    }
}
