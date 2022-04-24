package com.ridango.retro.retro.repository;

import com.ridango.retro.retro.entity.BoardColumnModel;
import com.ridango.retro.retro.entity.BoardModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetroBoardRepository extends JpaRepository<BoardModel, Long> {

    BoardColumnModel save(BoardColumnModel columnModel);
}
