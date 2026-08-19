package com.knu.RegistrationCertificate.controller;

import com.knu.RegistrationCertificate.dto.requestDto.StudentRequestDto;
import com.knu.RegistrationCertificate.dto.responseDto.StudentResponseDto;
import com.knu.RegistrationCertificate.repository.StudentRepository;
import com.knu.RegistrationCertificate.service.StudentService;
import com.lowagie.text.html.HtmlParser;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/createStudentDetail")
    public StudentResponseDto createStudentDetail(@ModelAttribute StudentRequestDto studentRequestDto) throws IOException {
        return studentService.createStudent(studentRequestDto);
    }

    @GetMapping("/getStudentDetail/{id}")
    public StudentResponseDto getStudentDetail(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/getAllStudentDetails")
    public List<StudentResponseDto> getAllStudentDetails() {
        return studentService.getAll();
    }

    @GetMapping("/generateRegistrationCertificate/{id}")
    public HttpEntity<byte[]> generateRegistrationCertificate(@PathVariable Long id) throws JRException {
        byte[] registrationCertificate = studentService.generateRegistrationCertificate(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment","Registration Certificate.pdf");
        return new HttpEntity<>(registrationCertificate, headers);
    }
}
