package pl.gr.veterinaryapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import pl.gr.veterinaryapp.model.dto.RaportResponseDto;
import pl.gr.veterinaryapp.model.dto.VetResponseRaportDto;
import pl.gr.veterinaryapp.repository.VisitRepository;
import pl.gr.veterinaryapp.service.impl.RaportServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RaportServiceTest {

    private static final User USER = new User("name", "passwd", Collections.emptySet());

    @Mock
    private VisitRepository visitRepository;
    @InjectMocks
    private RaportServiceImpl raportService;

    @Test
    void getRaport_DataCorrect_RaportReturned() {
        int month = 9;
        int year = 2023;
        int visits = 0;
        List<VetResponseRaportDto> eachVetVisits = emptyList();
        BigDecimal profit = BigDecimal.valueOf(0.0);
        RaportResponseDto raportResponseDto = new RaportResponseDto();
        LocalDate date = raportResponseDto.setNewDate(month, year);
        raportResponseDto.setNewRaport(profit, eachVetVisits, visits, date);

        when(visitRepository.countFinishedVisitByYearAndMonth(eq(month), eq(year))).thenReturn(visits);
        when(visitRepository.getAllVetDetailsByYearAndMonth(eq(month), eq(year))).thenReturn(eachVetVisits);
        when(visitRepository.countFinishedVisitsProfitPerYearAndMonth(eq(month), eq(year))).thenReturn(profit);

        var result = raportService.getRaport(USER, month, year);
        assertThat(result)
                .isNotNull()
                .isEqualTo(raportResponseDto);
        verify(visitRepository).countFinishedVisitByYearAndMonth(month, year);
        verify(visitRepository).getAllVetDetailsByYearAndMonth(month, year);
        verify(visitRepository).countFinishedVisitsProfitPerYearAndMonth(month, year);
    }
}
