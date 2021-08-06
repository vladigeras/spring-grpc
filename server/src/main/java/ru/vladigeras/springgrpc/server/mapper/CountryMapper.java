package ru.vladigeras.springgrpc.server.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.vladigeras.springgrpc.api.CountryDto;
import ru.vladigeras.springgrpc.api.CreateCountryRq;
import ru.vladigeras.springgrpc.api.CreateCountryRs;
import ru.vladigeras.springgrpc.server.model.entity.CountryEntity;

@Mapper
public interface CountryMapper {

    @Mapping(target = "title", source = "title")
    @BeanMapping(ignoreByDefault = true)
    CountryEntity map(CreateCountryRq createCountryDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @BeanMapping(ignoreByDefault = true)
    CountryDto map(CountryEntity countryEntity);

    @Mapping(target = "id", source = "id")
    @BeanMapping(ignoreByDefault = true)
    CreateCountryRs mapRs(CountryEntity countryEntity);
}
