package de.arkem.garage.management.order.adapter.out.db;

import de.arkem.camoleculs.AdapterModel;
import de.arkem.camoleculs.AdapterRing;
import de.arkem.camoleculs.OutputAdapter;
import de.arkem.garage.management.order.domain.model.GarageOrder;
import de.arkem.garage.management.order.usecase.out.SaveGarageOrder;
import lombok.RequiredArgsConstructor;
import org.jmolecules.ddd.annotation.Repository;
import org.springframework.stereotype.Component;

@AdapterRing
@OutputAdapter
@Repository
@Component
@RequiredArgsConstructor
public class GarageOrderRepository implements SaveGarageOrder {

    private final GarageOrderJpaRepository jpaRepository;
    private final GarageOrderToDbEntityMapper mapper;

    @Override
    public GarageOrder save(GarageOrder garageOrder) {
        var savedOrder = jpaRepository.save(
                mapper.mapToDbEntity(garageOrder));
        return mapper.mapToDomain(savedOrder);
    }
}
