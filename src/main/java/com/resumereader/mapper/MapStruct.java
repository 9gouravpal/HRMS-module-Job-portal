package com.resumereader.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.resumereader.dto.UserDetailDto;
import com.resumereader.entity.UserDetail;

@Mapper(componentModel = "Spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MapStruct {

	UserDetail convertToUserDetail(UserDetailDto dto);

	UserDetailDto convertToDto(UserDetail userDetail);

	List<UserDetailDto> convertToDtolist(List<UserDetail> listdtoDetailDtos);

}
