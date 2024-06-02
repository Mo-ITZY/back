package Collabo.MoITZY.web.controller;

import Collabo.MoITZY.web.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class FestivalController {

    private final FestivalService festivalService;

    @PostMapping("/mo-itzy/festivals")
    public String showFestivals() {
        return "축제 등록 완료";
    }

}
