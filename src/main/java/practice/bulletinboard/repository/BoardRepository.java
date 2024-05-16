package practice.bulletinboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import practice.bulletinboard.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // update board hits
    @Modifying
    @Query("update Board b set b.boardHits = b.boardHits + 1 where b.id = :id")
    public void updateHits(@Param("id") Long id);
}
