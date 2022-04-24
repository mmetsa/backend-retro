package com.ridango.retro.retro.service;

import com.ridango.retro.retro.dto.BoardColumnItemRequest;
import com.ridango.retro.retro.dto.RetroBoardRequest;
import com.ridango.retro.retro.dto.RetroBoardResponse;
import com.ridango.retro.retro.entity.BoardColumnModel;
import com.ridango.retro.retro.entity.BoardItemModel;
import com.ridango.retro.retro.entity.BoardModel;
import com.ridango.retro.retro.mapper.RetroBoardMapper;
import com.ridango.retro.retro.repository.RetroBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RetroBoardService {

    private final RetroBoardRepository repository;

    public RetroBoardResponse getRetroBoard(Long id) {
        return RetroBoardMapper.INSTANCE.toResponse(
                repository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BOARD_NOT_FOUND")));
    }

    public Long createRetroBoard(RetroBoardRequest request) {
        BoardModel model = RetroBoardMapper.INSTANCE.toModel(request);
        model = repository.save(model);
        return model.getId();
    }

    public void addItemToBoardColumn(BoardColumnItemRequest request) {
        BoardModel board = repository.getById(request.getBoardId());
        BoardColumnModel column = board.getColumns()
                .stream()
                .filter(c -> c.getId().equals(request.getColumnId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BOARD_NOT_FOUND"));

        BoardItemModel item = new BoardItemModel();
        item.setBoardColumnModel(column);
        item.setAuthor(request.getAuthor());
        item.setValue(request.getValue());
        if (column.getItems() != null) {
            column.getItems().add(item);
        } else {
            column.setItems(new ArrayList<>(List.of(item)));
        }
        repository.save(column);
    }

}
