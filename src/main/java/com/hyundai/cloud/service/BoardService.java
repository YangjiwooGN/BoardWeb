package com.hyundai.cloud.service;

import com.hyundai.cloud.dto.BoardDto;
import com.hyundai.cloud.entity.BoardEntity;
import com.hyundai.cloud.service.KafkaProducerService;
import com.hyundai.cloud.repository.BoardRepository;
import com.hyundai.cloud.repository.BoardMongoRepository;
import com.hyundai.cloud.document.BoardDocument;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardMongoRepository boardMongoRepository;
    @Autowired
    private KafkaProducerService kafkaProducerService;

    public List<BoardDocument> getAllBoards() {
        return boardMongoRepository.findAll();
    }

    public Optional<BoardDocument> getBoardById(int id) {
        return boardMongoRepository.findById(String.valueOf(id));
    }

    public BoardEntity saveBoard(BoardEntity board) {
        BoardEntity saved = boardRepository.save(board);
        kafkaProducerService.sendBoard(saved);
        return saved;
    }

    public void deleteBoard(int id) {
        boardRepository.deleteById(id);
        kafkaProducerService.deleteBoard(id);
    }

    public void createPost(BoardDto boardDto) {
        BoardEntity board = new BoardEntity();
        board.setBoardTitle(boardDto.getBoardTitle());
        board.setBoardContent(boardDto.getBoardContent());
        board.setBoardWriterEmail(boardDto.getBoardWriterEmail());
        board.setBoardWriterNickname(boardDto.getBoardWriterNickname());
        board.setBoardWriteDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        BoardEntity saved = boardRepository.save(board);
        kafkaProducerService.sendBoard(saved);
    }

    public void incrementViewCount(int id) {
        BoardEntity board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found!"));
        board.setBoardClickCount(board.getBoardClickCount() + 1);
        BoardEntity saved = boardRepository.save(board);
        kafkaProducerService.sendBoard(saved);
    }

    public Optional<BoardEntity> getBoardEntityById(int id) {
        return boardRepository.findById(id);
    }

}
