package pl.gr.veterinaryapp.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.web.servlet.MockMvc;
import pl.gr.veterinaryapp.config.WebSecurityConfig;
import pl.gr.veterinaryapp.jwt.JwtAuthenticationFilter;
import pl.gr.veterinaryapp.model.dto.RaportResponseDto;
import pl.gr.veterinaryapp.model.dto.VetResponseRaportDto;
import pl.gr.veterinaryapp.service.RaportService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = RaportRestController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                        classes = {WebSecurityConfigurerAdapter.class, JwtAuthenticationFilter.class})
        },
        excludeAutoConfiguration = {WebSecurityConfig.class})
public class RaportRestControllerTest {

    @MockBean
    private RaportService raportService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void getRaport_CorrectData_Returned() throws Exception {
        int month = 9;
        int year = 2023;
        int visits = 0;
        List<VetResponseRaportDto> eachVetVisits = emptyList();
        BigDecimal profit = BigDecimal.valueOf(0.0);
        RaportResponseDto raportResponseDto = new RaportResponseDto();
        LocalDate date = raportResponseDto.setNewDate(month, year);
        raportResponseDto.setNewRaport(profit, eachVetVisits, visits, date);

        when(raportService.getRaport(any(User.class), eq(month), eq(year))).thenReturn(raportResponseDto);

        mockMvc.perform(get("/api/raport/month/{month}/year/{year}", month, year))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.visits").value(visits))
                .andExpect(jsonPath("$.eachVetVisits", hasSize(0)))
                .andExpect(jsonPath("$.profit").value(profit));

        verify(raportService).getRaport(any(User.class), eq(month), eq(year));
    }
}
