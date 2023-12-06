package com.kameleoon.repository;

import com.kameleoon.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByQuoteIdOrderByCreatedAtAsc(Long quoteId);
}
