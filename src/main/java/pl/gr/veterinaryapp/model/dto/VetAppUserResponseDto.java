package pl.gr.veterinaryapp.model.dto;

import lombok.Data;

@Data
public class VetAppUserResponseDto {

    private Long id;
    private String username;
    private String password;
    private long role;
}
