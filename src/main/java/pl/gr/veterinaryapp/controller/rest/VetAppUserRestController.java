package pl.gr.veterinaryapp.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.gr.veterinaryapp.model.dto.VetAppUserRequestDto;
import pl.gr.veterinaryapp.model.dto.VetAppUserResponseDto;
import pl.gr.veterinaryapp.service.VetAppUserService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/users")
@RestController
public class VetAppUserRestController {

    private final VetAppUserService vetAppUserService;

    @GetMapping
    public List<VetAppUserResponseDto> getAllUsers() {

        return vetAppUserService.getAllUsers();
    }

    @PostMapping
    public VetAppUserResponseDto createUser(@RequestBody VetAppUserRequestDto user) {
        return vetAppUserService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        vetAppUserService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public VetAppUserResponseDto getUser(@PathVariable long id) {

        return vetAppUserService.getUser(id);
    }
}
