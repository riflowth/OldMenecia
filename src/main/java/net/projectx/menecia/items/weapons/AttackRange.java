package net.projectx.menecia.items.weapons;

public enum AttackRange {

    VERY_SHORT(1),
    SHORT(2),
    MEDIUM(3),
    LONG(4),
    VERY_LONG(5);

    private final int blockRange;

    AttackRange(int blockRange) {
        this.blockRange = blockRange;
    }

    public int getBlockRange() {
        return blockRange;
    }

}
