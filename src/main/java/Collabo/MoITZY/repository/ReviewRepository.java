package Collabo.MoITZY.repository;

import Collabo.MoITZY.domain.Review;
import Collabo.MoITZY.repository.dynamic.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {
}