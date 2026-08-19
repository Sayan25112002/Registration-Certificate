package com.knu.RegistrationCertificate.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponseDto {

    private Long id;

    private String name;

    private String parentName;

    private String universityName;

    private String registrationNumber;

    private String year;

    private String photo;

    private String signature;

    private String registrar;

}
