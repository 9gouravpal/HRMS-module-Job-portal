package com.resumereader.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resumereader.entity.ResumeUpload;

@Repository
public interface ResumeUploadRepository extends JpaRepository<ResumeUpload, Long> {

	ResumeUpload findByCode(String code);

}
