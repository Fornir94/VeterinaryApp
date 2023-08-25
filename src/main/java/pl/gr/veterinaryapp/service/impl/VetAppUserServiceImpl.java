package pl.gr.veterinaryapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gr.veterinaryapp.exception.IncorrectDataException;
import pl.gr.veterinaryapp.exception.ResourceNotFoundException;
import pl.gr.veterinaryapp.mapper.VetAppUserMapper;
import pl.gr.veterinaryapp.model.dto.VetAppUserRequestDto;
import pl.gr.veterinaryapp.model.dto.VetAppUserResponseDto;
import pl.gr.veterinaryapp.model.entity.Role;
import pl.gr.veterinaryapp.model.entity.VetAppUser;
import pl.gr.veterinaryapp.repository.VetAppUserRepository;
import pl.gr.veterinaryapp.service.VetAppUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VetAppUserServiceImpl implements VetAppUserService {

    private final VetAppUserRepository vetAppUserRepository;
    private final PasswordEncoder encoder;

    private final VetAppUserMapper mapper;

    @Override
    public List<VetAppUserResponseDto> getAllUsers() {
        return mapper.mapAsList(vetAppUserRepository.findAll());
    }

    @Override
    public VetAppUserResponseDto getUser(long id) {
        return vetAppUserRepository.findById(id).stream()
                .map(mapper::mapToDto)
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Wrong id."));
    }

    @Override
    @Transactional
    public VetAppUserResponseDto createUser(VetAppUserRequestDto user) {
        vetAppUserRepository.findByUsername(user.getUsername())
                .ifPresent(u -> {
                    throw new IncorrectDataException("Username exists.");
                });
        VetAppUser newVetAppUser = new VetAppUser();
        newVetAppUser.setUsername(user.getUsername());
        newVetAppUser.setPassword(encoder.encode(user.getPassword()));
        newVetAppUser.setRole(new Role(user.getRole()));
        return mapper.mapToDto(vetAppUserRepository.save(newVetAppUser));
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        var user = vetAppUserRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Wrong id."));
        vetAppUserRepository.delete(user);
    }
}
