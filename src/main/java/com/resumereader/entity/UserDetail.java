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
@Table(name = "user_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_user")
	@SequenceGenerator(name = "id_user", sequenceName = "user_sequence", allocationSize = 1)
	@Column(name = "id")
	private Long id;
	private String name;
	private String disgnation;
	@Column(name = "email", unique = true)
	private String email;
	private String mobileNo;
	private String address;
	private String country;
	private String state;
	private String city;
	private String relatedTo;
	private LocalDate date;
	private String code;
	private Boolean status = false;
}
