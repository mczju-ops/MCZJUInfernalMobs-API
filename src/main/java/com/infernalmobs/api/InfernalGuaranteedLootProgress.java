package com.infernalmobs.api;

import java.util.Objects;

/** 玩家某一条共享保底进度的只读快照。 */
public record InfernalGuaranteedLootProgress(
        String progressId,
        int progress,
        boolean completed
) {

    public InfernalGuaranteedLootProgress {
        progressId = Objects.requireNonNull(progressId, "progressId");
        progress = Math.max(0, progress);
    }
}
