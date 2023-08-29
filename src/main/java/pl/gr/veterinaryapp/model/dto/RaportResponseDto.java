package pl.gr.veterinaryapp.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class RaportResponseDto {

    private LocalDate date;
    private int visits;
    private List<VetResponseRaportDto> vetVisitsDetails;
    private BigDecimal profit;

    public void setNewRaport(BigDecimal profit, List<VetResponseRaportDto> vetVisitsDetails, int visits, LocalDate date) {
        this.profit = profit;
        this.vetVisitsDetails = vetVisitsDetails;
        this.visits = visits;
        this.date = date;
    }

    public LocalDate setNewDate(int month, int year){
       return LocalDate.of(year, month, 1);
    }
}
