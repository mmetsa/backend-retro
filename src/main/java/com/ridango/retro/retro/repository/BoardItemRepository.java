package com.ridango.retro.retro.repository;

import com.ridango.retro.retro.entity.BoardItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardItemRepository extends JpaRepository<BoardItemModel, Long> {
}
