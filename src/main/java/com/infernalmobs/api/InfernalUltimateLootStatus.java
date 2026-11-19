package com.infernalmobs.api;

import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/** 玩家某条终极战利品规则的保底积分快照。 */
public record InfernalUltimateLootStatus(
        String lootId,
        Set<EntityType> entityTypes,
        Map<Integer, BigDecimal> naturalChances,
        BigDecimal guaranteeThreshold,
        BigDecimal totalPityPoints,
        int guaranteedCount,
        int totalObtained,
        int compensationCount,
        int minimumMobLevel,
        @Nullable Integer maximumMobLevel,
        String rewardItemId,
        String rewardDisplayName,
        int rewardAmount
) {
    public InfernalUltimateLootStatus {
        lootId = Objects.requireNonNull(lootId, "lootId");
        entityTypes = entityTypes == null ? Set.of() : Set.copyOf(entityTypes);
        naturalChances = naturalChances == null ? Map.of() : Map.copyOf(naturalChances);
        guaranteeThreshold = nonNegative(guaranteeThreshold);
        totalPityPoints = nonNegative(totalPityPoints);
        guaranteedCount = Math.max(0, guaranteedCount);
        totalObtained = Math.max(0, totalObtained);
        compensationCount = Math.max(0, compensationCount);
        minimumMobLevel = Math.max(1, minimumMobLevel);
        rewardItemId = rewardItemId != null ? rewardItemId : "";
        rewardDisplayName = rewardDisplayName != null ? rewardDisplayName : rewardItemId;
        rewardAmount = Math.max(1, rewardAmount);
    }

    /** 根据当前积分和阈值计算理论最低保障件数。 */
    public int calculatedGuaranteedCount() {
        if (guaranteeThreshold.signum() <= 0) return guaranteedCount;
        BigInteger count = totalPityPoints.divide(guaranteeThreshold, 0, RoundingMode.FLOOR).toBigInteger();
        return count.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0
                ? Integer.MAX_VALUE : count.intValue();
    }

    private static BigDecimal nonNegative(BigDecimal value) {
        if (value == null || value.signum() < 0) return BigDecimal.ZERO;
        return value;
    }
}
