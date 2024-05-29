package Collabo.MoITZY.repository.dynamic;

import Collabo.MoITZY.domain.QReview;
import Collabo.MoITZY.dto.ReviewDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReviewRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    // 리뷰 조회
    @Override
    public List<ReviewDto> findReviewDtos(Long festivalId) {
        QReview review = QReview.review;
        List<ReviewDto> result = queryFactory.select(Projections.constructor(ReviewDto.class,
                        review.member.img,
                        review.member.name,
                        review.img,
                        review.content))
                .from(review)
                .where(review.festival.id.eq(festivalId))
                .fetch();
        return result;
    }
}