package pl.gr.veterinaryapp.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class VetResponseRaportDto {

    private Long id;
    private String name;
    private String surname;
    private Long numberOfVisits;
    private BigDecimal visitsProfit;

    public VetResponseRaportDto(Long id, String name, String surname, Long numberOfVisits, BigDecimal visitsProfit){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.numberOfVisits = numberOfVisits;
        this.visitsProfit = visitsProfit;
    }
}
