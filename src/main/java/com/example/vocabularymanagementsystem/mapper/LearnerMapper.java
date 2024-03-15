package com.example.vocabularymanagementsystem.mapper;

import com.example.vocabularymanagementsystem.dto.LearnerDTO;
import com.example.vocabularymanagementsystem.entity.Learner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LearnerMapper {

    LearnerMapper INSTANCE = Mappers.getMapper(LearnerMapper.class);

    @Mappings({
            @Mapping(target = "learnerId", source = "entity.learnerId"),
            @Mapping(target = "username", source = "entity.username"),
            @Mapping(target = "email", source = "entity.email"),
            @Mapping(target = "words", source = "entity.words")
    })
    LearnerDTO learnerToDto(Learner entity);

    @Mappings({
            @Mapping(target = "learnerId", source = "dto.learnerId"),
            @Mapping(target = "username", source = "dto.username"),
            @Mapping(target = "email", source = "dto.email"),
            @Mapping(target = "words", source = "dto.words")
    })
    Learner dtoToLearner(LearnerDTO dto);
}
