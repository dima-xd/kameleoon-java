package com.kameleoon.service.impl;

import com.kameleoon.entity.Vote;
import com.kameleoon.repository.VoteRepository;
import com.kameleoon.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    @Override
    public List<Vote> getVoteEvolution(Long quoteId) {
        return voteRepository.findByQuoteIdOrderByCreatedAtAsc(quoteId);
    }
}
