package com.matthew050102.wareflow.warehouse;

import com.matthew050102.wareflow.security.CurrentUserId;
import com.matthew050102.wareflow.warehouse.dto.WarehouseRequest;
import com.matthew050102.wareflow.warehouse.dto.WarehouseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/warehouses")
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;

    @GetMapping("/{warehouseId}")
    public ResponseEntity<WarehouseResponse> getWarehouseDataById(
            @PathVariable String warehouseId,
            @CurrentUserId String userId
    ) {
        return ResponseEntity.ok(warehouseService.getWarehouseDataById(warehouseId, userId));
    }

    @PostMapping
    public ResponseEntity<WarehouseResponse> createWarehouse(
            @RequestBody WarehouseRequest request,
            @CurrentUserId String userId
    ) {
        return ResponseEntity.ok(warehouseService.createWarehouse(request, userId));
    }

    @PutMapping("/{warehouseId}")
    public ResponseEntity<WarehouseResponse> updateWarehouseById(
            @PathVariable String warehouseId,
            @CurrentUserId String userId,
            @RequestBody WarehouseRequest request
    ) {
        return ResponseEntity.ok(warehouseService.updateWarehouseById(warehouseId, userId, request));
    }

    @DeleteMapping("/{warehouseId}")
    public ResponseEntity<Void> deleteWarehouseById(
            @PathVariable String warehouseId,
            @CurrentUserId String userId
    ) {
        warehouseService.deleteWarehouseById(warehouseId, userId);
        return ResponseEntity.noContent().build();
    }

}
