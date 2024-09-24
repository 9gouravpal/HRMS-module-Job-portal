package com.resumereader.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.resumereader.dto.UserDetailDto;
import com.resumereader.entity.ResumeUpload;
import com.resumereader.entity.UserDetail;
import com.resumereader.mapper.MapStruct;
import com.resumereader.repository.ResumeUploadRepository;
import com.resumereader.repository.UserDetailRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailService {
	private final UserDetailRepository repository;
	private final MapStruct mapper;
	private ResumeUploadRepository resumeUploadRepository;

	public UserDetailService(UserDetailRepository repository, MapStruct mapper,
			ResumeUploadRepository resumeUploadRepository) {
		super();
		this.repository = repository;
		this.mapper = mapper;
		this.resumeUploadRepository = resumeUploadRepository;
	}

	public UserDetailDto uploadYourDetails(UserDetailDto dto, MultipartFile file) {
		try {
			UserDetail userDetail = mapper.convertToUserDetail(dto);
			userDetail.setDate(LocalDate.now());
			userDetail.setCode(codeGenrate(userDetail.getName(), this.repository.count()));
			userDetail = repository.saveAndFlush(userDetail);
			ResumeUpload upload = new ResumeUpload();
			upload.setUploadResume(file.getBytes());
			upload.setCode(userDetail.getCode());
			this.resumeUploadRepository.saveAndFlush(upload);
			dto = mapper.convertToDto(userDetail);
		} catch (Exception e) {
			log.error("user detailed not saved!", e.getMessage(), e.getStackTrace());

		}
		return dto;
	}

	public String codeGenrate(String name, Long no) {
		String[] ss = name.split(" ");
		return ss[0].toUpperCase() + no;
	}

	public List<UserDetail> getAll() {
		try {
			List<UserDetail> listdtoDetailDtos = repository.findAll();
			return listdtoDetailDtos;
		} catch (Exception e) {
			log.error("data not found!", e.getMessage());
			throw new NullPointerException("list data not found!");
		}
	}

	public ResumeUpload getResume(String code) {
		try {
			ResumeUpload detail = resumeUploadRepository.findByCode(code);
			return detail;
		} catch (Exception e) {
			log.error("error in fetching cv!", e.getMessage());
			throw new IllegalArgumentException("id not matched!");
		}
	}

	public boolean deleteByCode(String code) {
		Optional<UserDetail> resume = repository.findByCode(code);
		if (resume.isPresent()) {
			repository.delete(resume.get());
			return true;
		}
		return false;
	}

	public String updateStatus(String code) {

		try {
			Optional<UserDetail> optional = repository.findByCode(code);
			if (optional.isPresent()) {
				UserDetail dataDetail = optional.get();
				dataDetail.setStatus(true);
				repository.save(dataDetail);
			}

		} catch (Exception e) {
			log.error("error");
		}
		return "success";
	}

	public List<UserDetailDto> getAllStatusUpdateEmployee() {
		List<UserDetailDto> list = new ArrayList<>();
		try {
			List<UserDetail> userDetails = repository.findByStatus(true);
			list = mapper.convertToDtolist(userDetails);
		} catch (Exception e) {
			log.error("error: ", e);
			// return new ArrayList<>();
		}
		return list;
	}
}
