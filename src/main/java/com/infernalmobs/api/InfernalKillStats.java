package com.infernalmobs.api;

import java.util.Map;

/** 玩家炒鸡怪击杀统计的只读快照。 */
public record InfernalKillStats(
        Map<Integer, Integer> killsByLevel,
        int totalKills
) {

    public InfernalKillStats {
        killsByLevel = killsByLevel != null ? Map.copyOf(killsByLevel) : Map.of();
        totalKills = Math.max(0, totalKills);
    }

    /** 没有击杀记录时使用的空快照。 */
    public static InfernalKillStats empty() {
        return new InfernalKillStats(Map.of(), 0);
    }
}
