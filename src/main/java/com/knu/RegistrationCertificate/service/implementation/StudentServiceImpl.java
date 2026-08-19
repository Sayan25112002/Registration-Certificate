package com.knu.RegistrationCertificate.service.implementation;

import com.knu.RegistrationCertificate.dto.requestDto.StudentRequestDto;
import com.knu.RegistrationCertificate.dto.responseDto.StudentResponseDto;
import com.knu.RegistrationCertificate.entity.Student;
import com.knu.RegistrationCertificate.mapper.StudentMapper;
import com.knu.RegistrationCertificate.repository.StudentRepository;
import com.knu.RegistrationCertificate.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) throws IOException {
        Student student = studentMapper.toStudent(studentRequestDto);
        student.setPhoto(saveFile(studentRequestDto.getPhotoFile()));
        student.setSignature(saveFile(studentRequestDto.getSignatureFile()));
        student.setRegistrar(saveFile(studentRequestDto.getRegistrarFile()));
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }


    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Student not found"));
        return studentMapper.toStudentResponseDto(student);
    }

    @Override
    public List<StudentResponseDto> getAll() {
        List<Student> students = studentRepository.findAll();
        return studentMapper.toStudentResponseDtoList(students);
    }

    @Override
    public byte[] generateRegistrationCertificate(Long id) throws JRException {
        String resourceDir = System.getProperty("user.dir") + "\\src\\main\\resources\\report\\";
        Path resourcePath = Paths.get(resourceDir,"RegistrationCertificate.jrxml");
        JasperReport registerReport = JasperCompileManager.compileReport(resourcePath.toString());
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singletonList(student));
        Map<String, Object> data = new HashMap<>();
        for(Field f : student.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                data.put(f.getName(), f.get(student));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("data", data);
        JasperPrint jasperPrint = JasperFillManager.fillReport(registerReport, parameter, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private String saveFile(MultipartFile file) throws IOException {
        String uploadDir = System.getProperty("user.dir")+"\\src\\main\\resources\\webapp\\images\\";
        Files.createDirectories(Paths.get(uploadDir));
        String filename =  System.currentTimeMillis()+"_"+file.getOriginalFilename();
        Path path = Paths.get(uploadDir,filename);
        Files.write(path, file.getBytes());
        return path.toString();
    }
}
