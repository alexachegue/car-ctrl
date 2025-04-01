package carctrladminproject.example.carctrlsysadminproject;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<Reply> findByReviewId(Long reviewId);
}