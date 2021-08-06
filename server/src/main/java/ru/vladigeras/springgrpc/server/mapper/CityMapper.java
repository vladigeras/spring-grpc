package ru.vladigeras.springgrpc.server.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.vladigeras.springgrpc.api.CityDto;
import ru.vladigeras.springgrpc.api.CreateCityRq;
import ru.vladigeras.springgrpc.api.CreateCityRs;
import ru.vladigeras.springgrpc.server.model.entity.CityEntity;

@Mapper
public abstract class CityMapper {

    @Autowired
    protected CountryMapper countryMapper;

    @Mapping(target = "title", source = "title")
    @BeanMapping(ignoreByDefault = true)
    public abstract CityEntity map(CreateCityRq createCityRq);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "country", expression = "java(countryMapper.map(cityEntity.getCountry()))")
    @BeanMapping(ignoreByDefault = true)
    public abstract CityDto map(CityEntity cityEntity);

    @Mapping(target = "id", source = "id")
    @BeanMapping(ignoreByDefault = true)
    public abstract CreateCityRs mapRs(CityEntity cityEntity);
}
