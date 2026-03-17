package com.linktic.ms_products.product.infrastructure.persistence.mapper;

import com.linktic.ms_products.product.domain.model.ProductModel;
import com.linktic.ms_products.product.infrastructure.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {

    ProductEntity toEntity(ProductModel domain);

    ProductModel toDomain(ProductEntity entity);

    default Page<ProductModel> toDomainList(Page<ProductEntity> entities) {
        return entities.map(this::toDomain);
    }
}
