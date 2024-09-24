package com.resumereader.mapper;

import com.resumereader.dto.UserDetailDto;
import com.resumereader.entity.UserDetail;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-24T14:44:59+0530",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 21.0.3 (Eclipse Adoptium)"
)
@Component
public class MapStructImpl implements MapStruct {

    @Override
    public UserDetail convertToUserDetail(UserDetailDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserDetail userDetail = new UserDetail();

        userDetail.setAddress( dto.getAddress() );
        userDetail.setCity( dto.getCity() );
        userDetail.setCountry( dto.getCountry() );
        userDetail.setDisgnation( dto.getDisgnation() );
        userDetail.setEmail( dto.getEmail() );
        userDetail.setMobileNo( dto.getMobileNo() );
        userDetail.setName( dto.getName() );
        userDetail.setRelatedTo( dto.getRelatedTo() );
        userDetail.setState( dto.getState() );
        userDetail.setStatus( dto.getStatus() );

        return userDetail;
    }

    @Override
    public UserDetailDto convertToDto(UserDetail userDetail) {
        if ( userDetail == null ) {
            return null;
        }

        UserDetailDto userDetailDto = new UserDetailDto();

        userDetailDto.setAddress( userDetail.getAddress() );
        userDetailDto.setCity( userDetail.getCity() );
        userDetailDto.setCountry( userDetail.getCountry() );
        userDetailDto.setDisgnation( userDetail.getDisgnation() );
        userDetailDto.setEmail( userDetail.getEmail() );
        userDetailDto.setMobileNo( userDetail.getMobileNo() );
        userDetailDto.setName( userDetail.getName() );
        userDetailDto.setRelatedTo( userDetail.getRelatedTo() );
        userDetailDto.setState( userDetail.getState() );
        userDetailDto.setStatus( userDetail.getStatus() );

        return userDetailDto;
    }

    @Override
    public List<UserDetailDto> convertToDtolist(List<UserDetail> listdtoDetailDtos) {
        if ( listdtoDetailDtos == null ) {
            return null;
        }

        List<UserDetailDto> list = new ArrayList<UserDetailDto>( listdtoDetailDtos.size() );
        for ( UserDetail userDetail : listdtoDetailDtos ) {
            list.add( convertToDto( userDetail ) );
        }

        return list;
    }
}
