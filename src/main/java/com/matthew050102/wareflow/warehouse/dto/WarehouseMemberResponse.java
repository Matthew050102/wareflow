package com.matthew050102.wareflow.warehouse.dto;

import com.matthew050102.wareflow.warehouse.WarehouseRole;

public record WarehouseMemberResponse(
   String id,
   String userId,
   String username,
   WarehouseRole role
) {}
