package com.infernalmobs.api;

import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/** 玩家实际累计获得的终极战利品数量，包含自然掉落与保底补发。 */
public record InfernalPlayerUltimateLootStats(
        UUID playerId,
        @Nullable String playerName,
        Map<String, Integer> totalObtainedByLootId
) {
    public InfernalPlayerUltimateLootStats {
        Objects.requireNonNull(playerId, "playerId");
        totalObtainedByLootId = Map.copyOf(totalObtainedByLootId);
    }
}
