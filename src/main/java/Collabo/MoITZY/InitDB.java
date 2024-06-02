package Collabo.MoITZY;

import Collabo.MoITZY.domain.*;
import Collabo.MoITZY.domain.embed.Address;
import Collabo.MoITZY.domain.embed.Period;
import Collabo.MoITZY.dto.FestivalApiDto;
import Collabo.MoITZY.web.repository.FestivalRepository;
import Collabo.MoITZY.web.service.FestivalService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitDB {

    private final DummyService initService;
    private final FestivalApiInitService festivalApiInitService;

    @PostConstruct
    public void init() {
        initService.dummyInit();
        festivalApiInitService.initApi();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class DummyService {
        private final EntityManager em;

        public void dummyInit() {
            Member member = new Member("tmdduq1999", "seungyeop", "1234", "tmdduq1999@naver.com", new Address("경상남도", "김해시", "부곡로 10", "413동 1403호"), null);
            em.persist(member);

            Festival festival = new Festival("부산연등회", "축제 이미지 주소", 0.0, 0.0, "교통 정보", "무료", "051)123-4567", "홈페이지 주소", "상세 설명", "편의시설", new Address("부산광역시", "부산진구", "부전동", "부산광역시 부산진구 부전동 1-1"), new Period(LocalDateTime.now(), LocalDateTime.now()));
            em.persist(festival);

            ROI roi = new ROI(member, festival);
            em.persist(roi);

            Review review = new Review(member, festival, "리뷰 이미지 주소", "리뷰 내용");
            em.persist(review);

            Inform inform = new Inform("공지사항1", "공지사항 내용1", LocalDateTime.now());
            em.persist(inform);

            List<Review> reviews1 = member.getReviews();
            for (Review review1 : reviews1) {
                log.info("before review1 = {}", review1);
            }

            List<ROI> roiList1 = member.getRoiList();
            for (ROI roi1 : roiList1) {
                log.info("before roi1 = {}", roi1);
            }

            member.addROI(roi);
            member.addReview(review);
            festival.addROI(roi);
            festival.addReview(review);

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

    @Component
    @Transactional
    @RequiredArgsConstructor
    @Slf4j
    static class FestivalApiInitService {
        private static final String API_URL = "http://apis.data.go.kr/6260000/FestivalService/getFestivalKr";
        private static final String API_KEY = "MvwRp6lI%2B00uP5QO2M1i1tLd2Rfn5pCVX0byg6cZLvujlrwGQt0EfinPijBsw8XCuiYQRvTTp%2F1%2F13BjQ%2Bka7Q%3D%3D";

        private final FestivalRepository festivalRepository;
        private final FestivalService festivalService;

        public void initApi() {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            // API 키 디코딩
            String decodedApiKey = URLDecoder.decode(API_KEY, StandardCharsets.UTF_8);

            String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                    .queryParam("resultType", "JSON")
                    .queryParam("numOfRows", 10)
                    .queryParam("pageNo", 1)
                    .queryParam("serviceKey", decodedApiKey)
                    .toUriString();

            log.info("API_KEY: {}", API_KEY);
            log.info("Decoded API_KEY: {}", decodedApiKey);
            log.info("url: {}", url);

            String body = restTemplate.getForEntity(url, String.class).getBody();

            log.info("body: {}", body);

            ObjectMapper objectMapper = new ObjectMapper();

            FestivalApiDto festivalApiDto = null;
            try {
                festivalApiDto = objectMapper.readValue(body, FestivalApiDto.class);
            } catch (JsonProcessingException e) {
                log.error("API 호출 중 오류 발생", e);
                throw new RuntimeException(e);
            }

            if (festivalApiDto != null) {
                List<Festival> festivals = festivalService.convertToFestivals(festivalApiDto);
                festivalRepository.saveAll(festivals);
            }
        }
    }
}
