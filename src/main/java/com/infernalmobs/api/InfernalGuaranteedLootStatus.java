package com.infernalmobs.api;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * 玩家当前一条有效保底规则的只读状态。
 *
 * <p>{@code currentProgress} 与 {@code requiredProgress} 的单位均为等级掉落池抽取次数，
 * 不一定等同于炒鸡怪击杀数。共享同一 {@code progressId} 的轮换规则会读取同一份累计进度。
 */
public record InfernalGuaranteedLootStatus(
        String ruleId,
        String progressId,
        int currentProgress,
        int requiredProgress,
        boolean completed,
        boolean repeatable,
        int minimumMobLevel,
        @Nullable Integer maximumMobLevel,
        String rewardItemId,
        String rewardDisplayName,
        int rewardAmount
) {

    public InfernalGuaranteedLootStatus {
        ruleId = Objects.requireNonNull(ruleId, "ruleId");
        progressId = Objects.requireNonNull(progressId, "progressId");
        currentProgress = Math.max(0, currentProgress);
        requiredProgress = Math.max(1, requiredProgress);
        minimumMobLevel = Math.max(1, minimumMobLevel);
        rewardItemId = rewardItemId != null ? rewardItemId : "";
        rewardDisplayName = rewardDisplayName != null ? rewardDisplayName : rewardItemId;
        rewardAmount = Math.max(1, rewardAmount);
    }

    /** 距离下一次触发还需要累计的等级掉落池抽取次数。 */
    public int remainingProgress() {
        return completed ? 0 : Math.max(0, requiredProgress - currentProgress);
    }

    /** 指定怪物等级是否会被这条保底规则统计。 */
    public boolean appliesToLevel(int mobLevel) {
        return mobLevel >= minimumMobLevel
                && (maximumMobLevel == null || mobLevel <= maximumMobLevel);
    }
}
