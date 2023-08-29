package pl.gr.veterinaryapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import pl.gr.veterinaryapp.model.dto.VetRequestDto;
import pl.gr.veterinaryapp.model.dto.VetResponseDto;
import pl.gr.veterinaryapp.model.dto.VetResponseRaportDto;
import pl.gr.veterinaryapp.model.entity.Vet;
import pl.gr.veterinaryapp.model.entity.Visit;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VetMapper {

    Vet map(VetRequestDto vetRequestDto);

    VetResponseDto mapToDto(Vet vet);
}
