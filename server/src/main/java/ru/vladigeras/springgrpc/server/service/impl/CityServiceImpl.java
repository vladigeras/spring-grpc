package ru.vladigeras.springgrpc.server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vladigeras.springgrpc.api.*;
import ru.vladigeras.springgrpc.server.mapper.CityMapper;
import ru.vladigeras.springgrpc.server.model.entity.CityEntity;
import ru.vladigeras.springgrpc.server.model.entity.CountryEntity;
import ru.vladigeras.springgrpc.server.model.exception.EntityNotFoundException;
import ru.vladigeras.springgrpc.server.repository.CityRepository;
import ru.vladigeras.springgrpc.server.repository.CountryRepository;
import ru.vladigeras.springgrpc.server.service.CityService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final CityMapper cityMapper;

    @Override
    @Transactional
    public CreateCityRs save(CreateCityRq createCityRq) {
        Long countryId = createCityRq.getCountryId();
        CountryEntity country = countryRepository.findById(countryId).orElseThrow(() -> new EntityNotFoundException("Country not found with id = " + countryId));
        CityEntity city = cityMapper.map(createCityRq);
        city.setCountry(country);
        city = cityRepository.save(city);
        log.info("City was successfully saved, id = {}", city.getId());
        return cityMapper.mapRs(city);
    }

    @Override
    @Transactional
    public FindCityRs find(FindCityRq findCityRq) {
        List<CityDto> cities = cityRepository.findAllByCityTitleAndCountry(findCityRq.getCityTitle(), findCityRq.getCountryId()).stream().map(cityMapper::map).collect(Collectors.toList());
        return FindCityRs.newBuilder().addAllCities(cities).build();
    }
}
