package practice.bulletinboard.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import practice.bulletinboard.dto.BoardDTO;
import practice.bulletinboard.entity.Board;
import practice.bulletinboard.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO) throws IOException {
        // 파일 첨부 여부에 따른 로직 분리
        if (boardDTO.getBoardFile().isEmpty()) {
            // 첨부 파일 없음.
            boardRepository.save(Board.toSaveEntity(boardDTO));
        } else {
            // 첨부 파일 있음.
            /*
                1. DTO에 담김 파일을 꺼냄.
                2. 파일의 이름 가져옴.
                3. 서버 저장용 이름을 만듦
                4. 저장 경로 설정
                5. 해당 경로에 파일 저장
                6. board_table에 해당 데이터 save 처리
                7. board_file_table에 해당 데이터 save 처리
             */

            boardRepository.save(Board.toSaveFileEntity(boardDTO));
        }
    }

    @Transactional
    public List<BoardDTO> findAll() {
        return boardRepository.findAll().stream()
                .map(BoardDTO::toBoardDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    @Transactional
    public BoardDTO findById(Long id) {
        Optional<Board> findBoard = boardRepository.findById(id);
        if (findBoard.isPresent()) {
            return BoardDTO.toBoardDTO(findBoard.get());
        }

        return null;
    }

    public BoardDTO update(BoardDTO boardDTO) {
        boardRepository.save(
                Board.toUpdateEntity(boardDTO)
        );

        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 개수

        // 한 페이지 당 글을 3개씩 보여주고, id를 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<Board> boards =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("boardEntities.getContent() = " + boards.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boards.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boards.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boards.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boards.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boards.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boards.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boards.isLast()); // 마지막 페이지 여부

        return boards.map(BoardDTO::toPageBoardDTO);
    }
}
