package de.arkem.garage.management.order.adapter.out.db;

import de.arkem.camoleculs.AdapterRing;
import de.arkem.camoleculs.OutputAdapter;
import de.arkem.garage.management.order.adapter.out.db.entity.GarageOrderDbEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@AdapterRing
@OutputAdapter
@Repository
public interface GarageOrderJpaRepository extends CrudRepository<GarageOrderDbEntity, Long> {
}
