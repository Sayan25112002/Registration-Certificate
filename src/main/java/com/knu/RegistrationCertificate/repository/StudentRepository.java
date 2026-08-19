package com.knu.RegistrationCertificate.repository;

import com.knu.RegistrationCertificate.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
