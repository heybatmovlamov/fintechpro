package az.edu.fintechpro.mapper;

import az.edu.fintechpro.dao.entity.AccountEntity;
import az.edu.fintechpro.model.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.swing.*;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto entityToDto(AccountEntity accountEntity);

    List<AccountDto> entityListToDtoList(List<AccountEntity> accountEntity);

    AccountEntity dtoToEntity(AccountDto accountDto); // Ensure this signature matches

    List<AccountEntity> dtoListToEntityList(List<AccountDto> accountDto);
}

