package com.matthew050102.wareflow.warehouse;

public enum WarehouseRole {
    VIEWER(1),
    WORKER(2),
    MANAGER(3),
    OWNER(4);

    private final int level;

    WarehouseRole(int level) {
        this.level = level;
    }

    public boolean hasPermission(WarehouseRole requiredRole) {
        return this.level >= requiredRole.level;
    }
}
