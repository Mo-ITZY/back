package Collabo.MoITZY.web.service;

import Collabo.MoITZY.domain.Festival;
import Collabo.MoITZY.dto.FestivalApiDto;
import Collabo.MoITZY.web.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.*;


@Service
@Slf4j
@RequiredArgsConstructor
public class FestivalService {

    private final FestivalRepository festivalRepository;


    // api 데이터를 가져와서 저장
    public List<Festival> convertToFestivals(FestivalApiDto festivalApiDto) {
        return festivalApiDto.getData().stream()
                .map(Festival::ApiToFestival)
                .collect(toList());
    }
}