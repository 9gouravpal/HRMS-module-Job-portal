package com.resumereader.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.resumereader.entity.UserDetail;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

	void deleteByCode(String code);

//	Optional<UserDetail> findByCode(String code);

//	Optional<UserDetail> findByName(String name);

	Optional<UserDetail> findByCode(String code);

	// @Query("Select u from user_detail u where u.status=true")
	List<UserDetail> findByStatus(boolean b);

}
