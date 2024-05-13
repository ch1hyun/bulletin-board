package practice.bulletinboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.bulletinboard.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
