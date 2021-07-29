package ru.vladigeras.springgrpc.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vladigeras.springgrpc.mapper.CountryMapper;
import ru.vladigeras.springgrpc.model.dto.CreateCountryDto;
import ru.vladigeras.springgrpc.model.entity.CountryEntity;
import ru.vladigeras.springgrpc.repository.CountryRepository;
import ru.vladigeras.springgrpc.service.CountryService;
import ru.vladigeras.springgrpc.util.Logger;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    @Transactional
    public Long save(CreateCountryDto createCountryDto) {
        log.info("New country will be save {}", Logger.toJson(createCountryDto));
        CountryEntity country = countryMapper.map(createCountryDto);
        country = countryRepository.save(country);
        log.info("Country was successfully saved, id = {}", country.getId());
        return country.getId();
    }
}
