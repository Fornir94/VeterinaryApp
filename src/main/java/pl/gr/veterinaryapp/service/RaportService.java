package pl.gr.veterinaryapp.service;


import org.springframework.security.core.userdetails.User;
import pl.gr.veterinaryapp.model.dto.RaportResponseDto;

public interface RaportService {

    RaportResponseDto getRaport(User user, int month, int year);

}
