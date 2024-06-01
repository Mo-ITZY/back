package Collabo.MoITZY;

import Collabo.MoITZY.domain.*;
import Collabo.MoITZY.domain.embed.Address;
import Collabo.MoITZY.domain.embed.Period;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void Init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = new Member("tmdduq1999", "seungyeop", "1234", "tmdduq1999@naver.com", new Address("경상남도", "김해시", "부곡로 10", "413동 1403호"), null);
            em.persist(member);

            Festival festival = new Festival("부산연등회", "축제 이미지 주소", "부산광역시불교연합회", "2568부산연등회봉행위원회", "051)867-0501~3", "부산광역시, 부산광역시불교연합신도회", "축제 상세 설명", new Address("부산광역시", "부산진구", "부전동", "부산광역시 부산진구 부전동 1-1"), new Period(LocalDateTime.now(), LocalDateTime.now()));
            em.persist(festival);

            ROI roi = new ROI(member, festival);
            em.persist(roi);

            Review review = new Review(member, festival, "리뷰 이미지 주소", "리뷰 내용");
            em.persist(review);

            Inform inform = new Inform("공지사항1", "공지사항 내용1", LocalDateTime.now());
            em.persist(inform);

            // 편의 메서드 적용 전에는 연관관계 주인 쪽에서만 값이 설정되어 있음
            List<Review> reviews1 = member.getReviews();
            for (Review review1 : reviews1) {
                log.info("before review1 = {}", review1);
            }

            List<ROI> roiList1 = member.getRoiList();
            for (ROI roi1 : roiList1) {
                log.info("before roi1 = {}", roi1);
            }

            // 양방향 연관관계 설정 - 편의 메서드 사용
            member.addROI(roi);
            member.addReview(review);
            festival.addROI(roi);
            festival.addReview(review);

            // 편의 메서드 적용 후에는 양쪽 모두 값이 설정되어 있음
            List<Review> reviews2 = member.getReviews();
            for (Review review2 : reviews2) {
                log.info("after review2 = {}", review2);
            }

            List<ROI> roiList2 = member.getRoiList();
            for (ROI roi2 : roiList2) {
                log.info("after roi2 = {}", roi2);
            }
        }
    }
}