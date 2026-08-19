package com.matthew050102.wareflow.warehouse.dto;

import com.matthew050102.wareflow.warehouse.WarehouseRole;

public record WarehouseMemberRequest(
        String userId,
        WarehouseRole role
) {}
