package ru.vladigeras.springgrpc.server.service;

import ru.vladigeras.springgrpc.api.CreateCountryRq;
import ru.vladigeras.springgrpc.api.CreateCountryRs;

public interface CountryService {

    CreateCountryRs save(CreateCountryRq createCountryDto);
}
