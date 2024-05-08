package Collabo.MoITZY.repository;

import Collabo.MoITZY.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}