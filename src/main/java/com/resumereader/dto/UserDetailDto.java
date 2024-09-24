package com.resumereader.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDetailDto {

	private String name;
	private String disgnation;
	private String email;
	private String mobileNo;
	private String address;
	private String country;
	private String state;
	private String city;
	private String relatedTo;
	private MultipartFile file;
	private Boolean status = false;
}
