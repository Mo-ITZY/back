package Collabo.MoITZY.web.controller;

import Collabo.MoITZY.domain.Inform;
import Collabo.MoITZY.web.service.InformService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class InformController {

    private final InformService informService;

    @GetMapping("/main")
    public List<Inform> getInforms() {
        return informService.getAllInforms();
    }
}