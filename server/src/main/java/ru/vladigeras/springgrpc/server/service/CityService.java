package ru.vladigeras.springgrpc.server.service;

import ru.vladigeras.springgrpc.api.CreateCityRq;
import ru.vladigeras.springgrpc.api.CreateCityRs;
import ru.vladigeras.springgrpc.api.FindCityRq;
import ru.vladigeras.springgrpc.api.FindCityRs;

public interface CityService {

    CreateCityRs save(CreateCityRq createCityDto);

    FindCityRs find(FindCityRq findCityRq);
}
