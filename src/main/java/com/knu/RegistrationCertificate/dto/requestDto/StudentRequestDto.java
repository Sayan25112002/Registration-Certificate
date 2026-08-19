package com.knu.RegistrationCertificate.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequestDto {

    private String name;

    private String parentName;

    private String universityName;

    private String registrationNumber;

    private String year;

    private String photo;

    private MultipartFile photoFile;

    private String signature;

    private MultipartFile signatureFile;

    private String registrar;

    private MultipartFile registrarFile;

}
