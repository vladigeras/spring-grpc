package ru.vladigeras.springgrpc.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vladigeras.springgrpc.model.dto.CountryDto;
import ru.vladigeras.springgrpc.model.dto.CreateCountryDto;
import ru.vladigeras.springgrpc.model.entity.CountryEntity;

@Mapper
public interface CountryMapper {

    @Mapping(target = "title", source = "title")
    @BeanMapping(ignoreByDefault = true)
    CountryEntity map(CreateCountryDto createCountryDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @BeanMapping(ignoreByDefault = true)
    CountryDto map(CountryEntity countryEntity);
}
