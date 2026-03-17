package com.linktic.ms_inventory.inventory.domain.repository;

import com.linktic.ms_inventory.inventory.domain.model.InventoryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface InventoryRepository {

    InventoryModel save(InventoryModel inventoryModel);

    Optional<InventoryModel> findByProductId(Long id);

}
