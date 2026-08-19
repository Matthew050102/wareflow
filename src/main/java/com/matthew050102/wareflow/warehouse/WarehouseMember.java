package com.matthew050102.wareflow.warehouse;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class WarehouseMember {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String warehouseId;
    private String userId;

    @Enumerated(EnumType.STRING)
    private WarehouseRole role;

    public WarehouseMember(String warehouseId, String userId, WarehouseRole role) {
        this.warehouseId = warehouseId;
        this.userId = userId;
        this.role = role;
    }
}
