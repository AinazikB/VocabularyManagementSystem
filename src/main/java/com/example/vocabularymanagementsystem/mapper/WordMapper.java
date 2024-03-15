package com.example.vocabularymanagementsystem.mapper;

import com.example.vocabularymanagementsystem.dto.WordDTO;
import com.example.vocabularymanagementsystem.entity.Word;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WordMapper {

    WordMapper INSTANCE = Mappers.getMapper(WordMapper.class);

    @Mappings({
            @Mapping(target = "wordId", source = "entity.wordId"),
            @Mapping(target = "originalWord", source = "entity.originalWord"),
            @Mapping(target = "translation", source = "entity.translation"),
            @Mapping(target = "difficultyLevel", source = "entity.difficultyLevel"),
            @Mapping(target = "status", source = "entity.status")
    })
    WordDTO wordToDto(Word entity);

    @Mappings({
            @Mapping(target = "wordId", source = "dto.wordId"),
            @Mapping(target = "originalWord", source = "dto.originalWord"),
            @Mapping(target = "translation", source = "dto.translation"),
            @Mapping(target = "difficultyLevel", source = "dto.difficultyLevel"),
            @Mapping(target = "status", source = "dto.status")
    })
    Word dtoToWord(WordDTO dto);
}
