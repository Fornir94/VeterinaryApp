package pl.gr.veterinaryapp.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.hateoas.MediaTypes;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.gr.veterinaryapp.model.dto.RaportResponseDto;
import pl.gr.veterinaryapp.service.RaportService;

@RequiredArgsConstructor
@RequestMapping("api/raport")
@RestController
public class RaportRestController {

    private final RaportService raportService;

    @GetMapping(path = "/month/{month}/year/{year}",produces = MediaTypes.HAL_JSON_VALUE)
    public RaportResponseDto getRaport(@AuthenticationPrincipal User user, @PathVariable int month, @PathVariable int year){
        return raportService.getRaport(user, month, year);
    }

}
