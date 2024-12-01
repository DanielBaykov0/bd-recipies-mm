package com.baykov.daniel.lang.repository.mapper;

import com.baykov.daniel.lang.entity.TranslationEntity;
import com.baykov.daniel.lang.payload.dto.response.TranslationResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface TranslationMapper {

    @Mapping(target = "key", source = "id.key")
    @Mapping(target = "value", source = "value")
    TranslationResponseDto entityToDto(TranslationEntity translation);

    List<TranslationResponseDto> entityToDto(List<TranslationEntity> translations);
}
