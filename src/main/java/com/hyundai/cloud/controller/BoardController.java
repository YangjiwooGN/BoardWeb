package com.hyundai.cloud.controller;

import com.hyundai.cloud.document.BoardDocument;
import com.hyundai.cloud.dto.BoardDto;
import com.hyundai.cloud.entity.BoardEntity;
import com.hyundai.cloud.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public ResponseEntity<List<BoardDocument>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDocument> getBoardById(@PathVariable int id) {
        return boardService.getBoardById(id)
                .map(board -> ResponseEntity.ok(board))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BoardEntity> createBoard(@RequestBody BoardEntity board) {
        return ResponseEntity.ok(boardService.saveBoard(board));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardEntity> updateBoard(@PathVariable int id,
                                                   @RequestPart("boardTitle") String boardTitle,
                                                   @RequestPart("boardContent") String boardContent) {
        BoardEntity existingBoard = boardService.getBoardEntityById(id)
                .orElseThrow(() -> new RuntimeException("Board not found!"));

        try {
            existingBoard.setBoardTitle(boardTitle);
            existingBoard.setBoardContent(boardContent);
            return ResponseEntity.ok(boardService.saveBoard(existingBoard));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable int id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Boolean>> createPost(@ModelAttribute BoardDto boardDto) {
        try {
            boardService.createPost(boardDto);

            Map<String, Boolean> response = new HashMap<>();
            response.put("success", true);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Boolean> response = new HashMap<>();
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/{id}/incrementView")
    public ResponseEntity<Void> incrementViewCount(@PathVariable int id) {
        boardService.incrementViewCount(id);
        return ResponseEntity.ok().build();
    }

}

