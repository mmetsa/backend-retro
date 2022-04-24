package com.ridango.retro.retro.controller;

import com.ridango.retro.retro.dto.BoardColumnItemRequest;
import com.ridango.retro.retro.dto.RetroBoardRequest;
import com.ridango.retro.retro.dto.RetroBoardResponse;
import com.ridango.retro.retro.service.RetroBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/retro")
@RequiredArgsConstructor
public class RetroController {

    private final RetroBoardService retroBoardService;

    @GetMapping("/board/{id}")
    public ResponseEntity<RetroBoardResponse> getRetroBoard(@PathVariable Long id) {
        return ResponseEntity.ok(retroBoardService.getRetroBoard(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createRetroBoard(@RequestBody RetroBoardRequest request) {
        return ResponseEntity.ok(retroBoardService.createRetroBoard(request));
    }

    @PostMapping("/addItem")
    public ResponseEntity<Void> addItemToBoardColumn(@RequestBody BoardColumnItemRequest request) {
        retroBoardService.addItemToBoardColumn(request);
        return ResponseEntity.ok().build();
    }

}
