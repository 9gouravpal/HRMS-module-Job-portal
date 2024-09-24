package com.resumereader.controller;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resumereader.dto.UserDetailDto;
import com.resumereader.entity.ResumeUpload;
import com.resumereader.entity.UserDetail;
import com.resumereader.service.UserDetailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/employee")
@Tag(name = "Employee-detailed")
public class UserDetailController {

	private final UserDetailService service;

	public UserDetailController(UserDetailService service) {
		super();
		this.service = service;
	}

	@PostMapping(value = "/addData", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "to employee detailed ", description = "this api is basically to filled employee detaliled!")
	public ResponseEntity<Object> postMethodDto(@ModelAttribute UserDetailDto dto) {
		UserDetailDto dto2 = service.uploadYourDetails(dto, dto.getFile());
		if (dto2 != null) {
			return new ResponseEntity<>(dto2, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Detailed not complete fill!", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getAll")
	@Operation(summary = "to employee list find", description = "this api is basically to find all data in list form")
	public ResponseEntity<Object> getListData() {
		List<UserDetail> list = service.getAll();
		if (list.isEmpty()) {
			return new ResponseEntity<>("data not found!", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@GetMapping("/getByEmpId")
	@Operation(summary = "to get resume", description = "this api is basically to find resume of employee")
	public ResponseEntity<Object> getImage(@RequestParam("code") String code) {
		ResumeUpload upload = service.getResume(code);
		if (upload == null) {
			return new ResponseEntity<>("cv not found!", HttpStatus.BAD_REQUEST);
		} else {
			String dataString = Base64.getEncoder().encodeToString(upload.getUploadResume());
			return new ResponseEntity<>(dataString, HttpStatus.OK);
		}
	}

	@GetMapping("/getDownload")
	@Operation(summary = "to download resume", description = "this api is basically to download employee resume")
	public ResponseEntity<Object> download(@RequestParam("code") String code) {
		ResumeUpload upload = service.getResume(code);
		if (upload == null) {
			return new ResponseEntity<>("CV not found!", HttpStatus.BAD_REQUEST);
		} else {
			byte[] content = upload.getUploadResume();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDisposition(ContentDisposition.builder("attachment").filename("resume.pdf").build());

			ByteArrayInputStream stream = new ByteArrayInputStream(content);

			return new ResponseEntity<>(new InputStreamResource(stream), headers, HttpStatus.OK);
		}
	}

	@DeleteMapping("/deleteByCode")
	public ResponseEntity<String> deleteByCode(@RequestParam("code") String code) {
		boolean isDeleted = service.deleteByCode(code);
		if (isDeleted) {
			return new ResponseEntity<>("Resume with code " + code + " deleted successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Resume with code " + code + " not found.", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateStatus")
	public ResponseEntity<String> statusUpdate(String code) {
		String messString = service.updateStatus(code);
		if (messString.equals("success")) {
			return new ResponseEntity<>("status updated", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("status updated", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getAll/markCandidate")
	public ResponseEntity<Object> getAllMarkCandidate() {
		List<UserDetailDto> list = service.getAllStatusUpdateEmployee();
		if (list.isEmpty()) {
			return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Object>(list, HttpStatus.ACCEPTED);
		}
	}
}