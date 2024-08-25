package az.edu.fintechpro.mapper;


import az.edu.fintechpro.dao.entity.UserEntity;
import az.edu.fintechpro.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto entityToDto(UserEntity userEntity);

    List<UserDto> entityListToDtoList(List<UserEntity> userEntity);

    UserEntity dtoToEntity(UserDto userDto);
List<UserEntity> dtoListToEntityList(List<UserDto> userDto);
}
