package com.toy.sakila.film.adapter.out.persistence;


import com.toy.sakila.film.domain.EnumSpecialFeature;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class SpecialFeaturesConverter implements AttributeConverter<Set<EnumSpecialFeature>, String> {

    @Override
    public String convertToDatabaseColumn(Set<EnumSpecialFeature> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "";
        }
        return attribute.stream().map(EnumSpecialFeature::name).collect(Collectors.joining(","));
    }

    @Override
    public Set<EnumSpecialFeature> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return new HashSet<>();
        }
        return Arrays.stream(dbData.split(","))
                .map(EnumSpecialFeature::valueOf)
                .collect(Collectors.toSet());
    }
}