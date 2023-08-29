package pl.gr.veterinaryapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.gr.veterinaryapp.exception.IncorrectDataException;
import pl.gr.veterinaryapp.exception.ResourceNotFoundException;
import pl.gr.veterinaryapp.mapper.AnimalMapper;
import pl.gr.veterinaryapp.model.dto.AnimalRequestDto;
import pl.gr.veterinaryapp.model.dto.AnimalResponseDto;
import pl.gr.veterinaryapp.model.entity.Animal;
import pl.gr.veterinaryapp.repository.AnimalRepository;
import pl.gr.veterinaryapp.service.AnimalService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper mapper;

    @Override
    public AnimalResponseDto getAnimalById(long id) {
        return animalRepository.findById(id).stream()
                .map(mapper::mapToDto)
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Wrong id."));
    }

    @Transactional
    @Override
    public AnimalResponseDto createAnimal(AnimalRequestDto animalRequestDto) {
        var animalCheck = animalRepository.findBySpecies(animalRequestDto.getSpecies());
        if (animalCheck.isPresent()) {
            throw new IncorrectDataException("Species exists.");
        }
        Animal animal = animalRepository.save(mapper.map(animalRequestDto));
        return mapper.mapToDto(animal);
    }

    @Transactional
    @Override
    public void deleteAnimal(long id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wrong id."));
        animalRepository.delete(animal);
    }

    @Override
    public List<AnimalResponseDto> getAllAnimals() {
        return animalRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }
}
