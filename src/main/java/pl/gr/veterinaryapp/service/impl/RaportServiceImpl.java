package pl.gr.veterinaryapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import pl.gr.veterinaryapp.model.dto.RaportResponseDto;
import pl.gr.veterinaryapp.model.dto.VetResponseRaportDto;
import pl.gr.veterinaryapp.repository.VisitRepository;
import pl.gr.veterinaryapp.service.RaportService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RaportServiceImpl implements RaportService {

    private final VisitRepository visitRepository;

    @Override
    public RaportResponseDto getRaport(User user, int month, int year) {
        int visits = visitRepository.countFinishedVisitByYearAndMonth(month, year);
        List<VetResponseRaportDto> eachVetVisits = visitRepository.getAllVetDetailsByYearAndMonth(month, year);
        BigDecimal profit = visitRepository.countFinishedVisitsProfitPerYearAndMonth(month, year);

        RaportResponseDto raport = new RaportResponseDto();
        LocalDate date = raport.setNewDate(month, year);
        raport.setNewRaport(profit, eachVetVisits, visits, date);
        return raport;
    }
}
