package pl.gr.veterinaryapp.service;

import pl.gr.veterinaryapp.model.dto.VetAppUserRequestDto;
import pl.gr.veterinaryapp.model.dto.VetAppUserResponseDto;

import java.util.List;

public interface VetAppUserService {

    List<VetAppUserResponseDto> getAllUsers();

    VetAppUserResponseDto getUser(long id);

    VetAppUserResponseDto createUser(VetAppUserRequestDto user);

    void deleteUser(long id);
}
