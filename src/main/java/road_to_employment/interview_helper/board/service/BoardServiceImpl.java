package road_to_employment.interview_helper.board.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import road_to_employment.interview_helper.board.entity.Board;
import road_to_employment.interview_helper.board.repository.BoardRepository;
import road_to_employment.interview_helper.board.service.request.BoardCreateRequest;
import road_to_employment.interview_helper.board.service.request.BoardUpdateRequest;
import road_to_employment.interview_helper.board.service.response.BoardCreateResponse;
import road_to_employment.interview_helper.board.service.response.BoardListResponse;
import road_to_employment.interview_helper.board.service.response.BoardReadResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@EnableJpaRepositories(basePackages = "road_to_employment.interview_helper.board")
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public BoardCreateResponse create(BoardCreateRequest boardCreateRequest) {
        log.info("board service -> create() called!");
        Board board = boardCreateRequest.toBoard();
        Board createdBoard = boardRepository.save(board);

        return BoardCreateResponse.from(createdBoard);
    }

    @Override
    public List<BoardListResponse> list() {
        log.info("board service -> list() called!");
        List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "updateDate"));

        return boardList.stream().map(BoardListResponse::from).collect(Collectors.toList());
    }

    @Override
    public BoardReadResponse read(Long id) {
        log.info("board service -> read() called!");
        Optional<Board> maybeBoard = boardRepository.findById(id);

        Board board = maybeBoard.orElse(null);
        return BoardReadResponse.from(board);
    }

    @Transactional
    @Override
    public BoardReadResponse update(Long id, BoardUpdateRequest boardUpdateRequest) {
        log.info("board service -> update() called!");
        Optional<Board> maybeBoard = boardRepository.findById(id);

        Board board = maybeBoard.orElse(null);

        if (board != null) {
            board.updatedBoard(
                    boardUpdateRequest.getTitle(),
                    boardUpdateRequest.getContent()
            );
        }

        return BoardReadResponse.from(board);
    }

    @Transactional
    @Override
    public Boolean delete(Long id) {
        log.info("board service -> delete() called!");
        Optional<Board> maybeBoard = boardRepository.findById(id);

        Board board = maybeBoard.orElse(null);

        if (board == null) {
            return false;
        }
        else {
            boardRepository.delete(board);
            return true;
        }
    }
}
