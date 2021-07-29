package ru.vladigeras.springgrpc.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vladigeras.springgrpc.mapper.CityMapper;
import ru.vladigeras.springgrpc.model.dto.CityDto;
import ru.vladigeras.springgrpc.model.dto.CreateCityDto;
import ru.vladigeras.springgrpc.model.entity.CityEntity;
import ru.vladigeras.springgrpc.model.entity.CountryEntity;
import ru.vladigeras.springgrpc.model.exception.EntityNotFoundException;
import ru.vladigeras.springgrpc.repository.CityRepository;
import ru.vladigeras.springgrpc.repository.CountryRepository;
import ru.vladigeras.springgrpc.service.CityService;
import ru.vladigeras.springgrpc.util.Logger;

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
    public Long save(CreateCityDto createCityDto) {
        log.info("New city will be save {}", Logger.toJson(createCityDto));
        Long countryId = createCityDto.getCountryId();
        CountryEntity country = countryRepository.findById(countryId).orElseThrow(() -> new EntityNotFoundException("Country not found with id = " + countryId));
        CityEntity city = cityMapper.map(createCityDto);
        city.setCountry(country);
        city = cityRepository.save(city);
        log.info("City was successfully saved, id = {}", city.getId());
        return city.getId();
    }

    @Override
    @Transactional
    public List<CityDto> find(String cityTitle, Long countryId) {
        return cityRepository.findAllByCityTitleAndCountry(cityTitle, countryId).stream().map(cityMapper::map).collect(Collectors.toList());
    }
}
