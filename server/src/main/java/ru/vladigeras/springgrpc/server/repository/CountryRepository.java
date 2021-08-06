package ru.vladigeras.springgrpc.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vladigeras.springgrpc.server.model.entity.CountryEntity;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
}
