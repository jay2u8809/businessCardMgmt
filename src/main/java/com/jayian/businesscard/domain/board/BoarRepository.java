package com.jayian.businesscard.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoarRepository extends JpaRepository<Board, Long> {
}
