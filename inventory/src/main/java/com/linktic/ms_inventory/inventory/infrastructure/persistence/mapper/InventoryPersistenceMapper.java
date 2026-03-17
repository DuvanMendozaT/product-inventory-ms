package com.linktic.ms_inventory.inventory.infrastructure.persistence.mapper;

import com.linktic.ms_inventory.inventory.domain.model.InventoryModel;
import com.linktic.ms_inventory.inventory.infrastructure.persistence.entity.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InventoryPersistenceMapper {


    InventoryPersistenceMapper INVENTORY_PERSISTENCE_MAPPER = Mappers.getMapper(InventoryPersistenceMapper.class);

    InventoryEntity toEntity(InventoryModel domain);

    InventoryModel toDomain(InventoryEntity entity);

}
