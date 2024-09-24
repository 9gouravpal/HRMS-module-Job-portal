package com.resumereader.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "resume_upload")
public class ResumeUpload {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_resume")
	@SequenceGenerator(name = "id_resume", sequenceName = "seq_resume", allocationSize = 1)
	@Column(name = "id")
	private Long imageId;
	@Column(name = "resume")
	private byte[] uploadResume;
	@Column(name = "date")
	private LocalDate resumeDate;
	private String code;

}
