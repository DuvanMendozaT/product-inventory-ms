package com.linktic.ms_inventory.inventory.infrastructure.persistence.adapter;

import com.linktic.ms_inventory.inventory.domain.model.InventoryModel;
import com.linktic.ms_inventory.inventory.domain.repository.InventoryRepository;
import com.linktic.ms_inventory.inventory.infrastructure.persistence.mapper.InventoryPersistenceMapper;
import com.linktic.ms_inventory.inventory.infrastructure.persistence.repository.JpaInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InventoryRepositoryAdapter implements InventoryRepository {

    @Autowired
    private JpaInventoryRepository jpaInventoryRepository;

    @Autowired
    private InventoryPersistenceMapper mapper;
    @Override
    public InventoryModel save(InventoryModel inventoryModel) {
        return mapper.toDomain(jpaInventoryRepository.save(mapper.toEntity(inventoryModel)));
    }

    @Override
    public Optional<InventoryModel> findByProductId(Long productId) {
        return jpaInventoryRepository.findByProductId(productId)
                .map(mapper::toDomain);
    }
}
