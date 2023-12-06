package com.kameleoon.service;

import com.kameleoon.entity.Vote;

import java.util.List;

public interface VoteService {
    List<Vote> getVoteEvolution(Long quoteId);
}
