package ru.vladigeras.springgrpc.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.vladigeras.springgrpc.model.entity.CityEntity;
import ru.vladigeras.springgrpc.model.entity.QCityEntity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.Objects.nonNull;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long>, QuerydslPredicateExecutor<CityEntity> {

    default List<CityEntity> findAllByCityTitleAndCountry(String cityTitle, Long countryId) {
        Sort sort = Sort.by(Sort.Direction.ASC, QCityEntity.cityEntity.title.getMetadata().getName());
        BooleanBuilder predicate = new BooleanBuilder();
        if (nonNull(cityTitle)) {
            BooleanExpression cityTitleExpression = QCityEntity.cityEntity.title.likeIgnoreCase("%" + cityTitle + "%");
            predicate.and(cityTitleExpression);
        }
        if (nonNull(countryId)) {
            BooleanExpression countryIdExpression = QCityEntity.cityEntity.country.id.eq(countryId);
            predicate.and(countryIdExpression);
        }
        return StreamSupport.stream(findAll(predicate, sort).spliterator(), false).collect(Collectors.toList());
    }
}
