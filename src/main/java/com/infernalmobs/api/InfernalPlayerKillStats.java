package com.infernalmobs.api;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.UUID;

/** 炒鸡怪击杀统计中的一名玩家及其只读统计快照。 */
public record InfernalPlayerKillStats(
        UUID playerId,
        @Nullable String playerName,
        InfernalKillStats stats
) {

    public InfernalPlayerKillStats {
        playerId = Objects.requireNonNull(playerId, "playerId");
        playerName = playerName != null && !playerName.isBlank() ? playerName.trim() : null;
        stats = stats != null ? stats : InfernalKillStats.empty();
    }
}
