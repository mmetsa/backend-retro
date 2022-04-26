package com.ridango.retro.retro.service;

import com.ridango.retro.retro.dto.BoardColumnItemRequest;
import com.ridango.retro.retro.dto.RetroBoardListResponse;
import com.ridango.retro.retro.dto.RetroBoardRequest;
import com.ridango.retro.retro.dto.RetroBoardResponse;
import com.ridango.retro.retro.entity.BoardColumnModel;
import com.ridango.retro.retro.entity.BoardItemModel;
import com.ridango.retro.retro.entity.BoardModel;
import com.ridango.retro.retro.mapper.RetroBoardMapper;
import com.ridango.retro.retro.repository.BoardItemRepository;
import com.ridango.retro.retro.repository.RetroBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RetroBoardService {

    private final RetroBoardRepository repository;
    private final BoardItemRepository boardItemRepository;

    public RetroBoardResponse getRetroBoard(Long id) {
        var response = RetroBoardMapper.INSTANCE.toResponse(
                repository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "BOARD_NOT_FOUND")));
        response.setActive(!response.getRetroDate().plusDays(2).isBefore(LocalDate.now()));
        return response;
    }

    public List<RetroBoardListResponse> getBoardList() {
        return RetroBoardMapper.INSTANCE.toListResponse(repository.findAll());
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

    public void removeItemFromBoardColumn(Long id) {
        boardItemRepository.deleteById(id);
    }

    public void addUpvoteToItem(Long itemId) {
        BoardItemModel model = boardItemRepository.getById(itemId);
        if (model.getUpVotes() == null) {
            model.setUpVotes(1);
        } else {
            model.setUpVotes(model.getUpVotes() + 1);
        }
        boardItemRepository.save(model);
    }

    public void addDownvoteToItem(Long itemId) {
        BoardItemModel model = boardItemRepository.getById(itemId);
        if (model.getDownVotes() == null) {
            model.setDownVotes(1);
        } else {
            model.setDownVotes(model.getDownVotes() + 1);
        }
        boardItemRepository.save(model);
    }

}
