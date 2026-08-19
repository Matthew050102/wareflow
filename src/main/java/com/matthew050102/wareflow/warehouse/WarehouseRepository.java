package com.matthew050102.wareflow.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, String> {
    Optional<Warehouse> findById(String id);
}
