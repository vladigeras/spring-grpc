package ru.vladigeras.springgrpc.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vladigeras.springgrpc.model.dto.CityDto;
import ru.vladigeras.springgrpc.model.dto.CreateCityDto;
import ru.vladigeras.springgrpc.model.entity.CityEntity;

@Mapper
public abstract class CityMapper {

    @Autowired
    protected CountryMapper countryMapper;

    @Mapping(target = "title", source = "title")
    @BeanMapping(ignoreByDefault = true)
    public abstract CityEntity map(CreateCityDto createCityDto);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "country", expression = "java(countryMapper.map(cityEntity.getCountry()))")
    @BeanMapping(ignoreByDefault = true)
    public abstract CityDto map(CityEntity cityEntity);
}
