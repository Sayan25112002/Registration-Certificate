package com.knu.RegistrationCertificate.mapper;

import com.knu.RegistrationCertificate.dto.requestDto.StudentRequestDto;
import com.knu.RegistrationCertificate.dto.responseDto.StudentResponseDto;
import com.knu.RegistrationCertificate.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toStudent(StudentRequestDto studentRequestDto);

    StudentResponseDto toStudentResponseDto(Student student);

    List<StudentResponseDto> toStudentResponseDtoList(List<Student> students);

}
