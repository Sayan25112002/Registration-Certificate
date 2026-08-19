package com.knu.RegistrationCertificate.service;

import com.knu.RegistrationCertificate.dto.requestDto.StudentRequestDto;
import com.knu.RegistrationCertificate.dto.responseDto.StudentResponseDto;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto studentRequestDto) throws IOException;

    StudentResponseDto getStudentById(Long id);

    List<StudentResponseDto> getAll();

    byte[] generateRegistrationCertificate(Long id) throws JRException;

}
