package ru.vladigeras.springgrpc.server.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.vladigeras.springgrpc.api.CreateCountryRq;
import ru.vladigeras.springgrpc.api.CreateCountryRs;
import ru.vladigeras.springgrpc.server.mapper.CountryMapper;
import ru.vladigeras.springgrpc.server.model.entity.CountryEntity;
import ru.vladigeras.springgrpc.server.repository.CountryRepository;
import ru.vladigeras.springgrpc.server.service.CountryService;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    @Transactional
    public CreateCountryRs save(CreateCountryRq createCountryDto) {
        CountryEntity country = countryMapper.map(createCountryDto);
        country = countryRepository.save(country);
        log.info("Country was successfully saved, id = {}", country.getId());
        return countryMapper.mapRs(country);
    }
}
