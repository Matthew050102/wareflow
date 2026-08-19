package com.matthew050102.wareflow.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WarehouseMemberRepository extends JpaRepository<WarehouseMember, String> {
    Optional<WarehouseMember> findById(String id);
    Optional<WarehouseMember> findByWarehouseIdAndUserId(String warehouseId, String userId);
    List<WarehouseMember> findByUserId(String userId);
    List<WarehouseMember> findByWarehouseId(String warehouseId);
}
