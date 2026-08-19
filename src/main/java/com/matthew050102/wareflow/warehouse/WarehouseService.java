package com.matthew050102.wareflow.warehouse;

import com.matthew050102.wareflow.warehouse.dto.WarehouseRequest;
import com.matthew050102.wareflow.warehouse.dto.WarehouseResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseMemberRepository warehouseMemberRepository;

    public WarehouseResponse getWarehouseDataById(String warehouseId, String userId) {
        Warehouse warehouse = findWarehouseById(warehouseId);
        findWarehouseMember(warehouseId, userId);

        return new WarehouseResponse(
                warehouse.getId(),
                warehouse.getName()
        );
    }

    @Transactional
    public WarehouseResponse createWarehouse(WarehouseRequest request, String userId) {
        if (request.name().isEmpty()) {
            throw new IllegalArgumentException("Warehouse name cannot be empty!");
        }

        Warehouse savedWarehouse = warehouseRepository.save(
                new Warehouse(request.name())
        );

        warehouseMemberRepository.save(new WarehouseMember(
                savedWarehouse.getId(),
                userId,
                WarehouseRole.OWNER
        ));

        return new WarehouseResponse(
                savedWarehouse.getId(),
                savedWarehouse.getName()
        );
    }

    @Transactional
    public WarehouseResponse updateWarehouseById(String warehouseId, String userId, WarehouseRequest request) {
        Warehouse warehouse = getWarehouseWithRoleCheck(warehouseId, userId, WarehouseRole.OWNER);
        warehouse.setName(request.name());
        Warehouse updatedWarehouse = warehouseRepository.save(warehouse);

        return new WarehouseResponse(
                updatedWarehouse.getId(),
                updatedWarehouse.getName()
        );
    }

    @Transactional
    public void deleteWarehouseById(String warehouseId, String userId) {
        Warehouse warehouse = getWarehouseWithRoleCheck(warehouseId, userId, WarehouseRole.OWNER);
        warehouseRepository.deleteById(warehouseId);
    }

    private Warehouse findWarehouseById(String id) {
        return warehouseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Warehouse with id '" + id + "' does not exist!"));
    }

    private WarehouseMember findWarehouseMember(String warehouseId, String userId) {
        return warehouseMemberRepository.findByWarehouseIdAndUserId(warehouseId, userId)
                .orElseThrow(() -> new AccessDeniedException("You are not a member of this warehouse!"));
    }

    private boolean memberHasPermission(WarehouseMember member, WarehouseRole requiredRole) {
        if (!member.getRole().hasPermission(requiredRole)) {
            throw new AccessDeniedException("Insufficient permission! Required level: " + requiredRole);
        }
        return true;
    }

    private Warehouse getWarehouseWithRoleCheck(String warehouseId, String userId, WarehouseRole requiredRole) {
        Warehouse warehouse = findWarehouseById(warehouseId);

        WarehouseMember member = findWarehouseMember(warehouseId, userId);
        memberHasPermission(member, requiredRole);

        return warehouse;
    }
}
