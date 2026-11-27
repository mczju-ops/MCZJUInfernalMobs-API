package com.infernalmobs.api.event.mob;

import com.infernalmobs.api.InfernalMobHandle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 玩家有效击杀匹配终极战利品规则的炒鸡怪后，在自然判定和保底积分累计前同步触发。
 * 最终掉率同时用于本次自然判定和本次保底积分增量。设为 0 时，已有积分产生的待补发仍可结算。
 */
public final class InfernalMobUltimateLootRollEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();

    private final InfernalMobHandle handle;
    private final Player killer;
    private final String lootId;
    private final BigDecimal originalNaturalChance;
    private BigDecimal naturalChance;

    public InfernalMobUltimateLootRollEvent(InfernalMobHandle handle, Player killer,
                                          String lootId, BigDecimal originalNaturalChance) {
        this.handle = Objects.requireNonNull(handle, "炒鸡怪句柄不能为空");
        this.killer = Objects.requireNonNull(killer, "击杀玩家不能为空");
        this.lootId = Objects.requireNonNull(lootId, "终极战利品规则 ID 不能为空");
        setNaturalChance(originalNaturalChance);
        this.originalNaturalChance = naturalChance;
    }

    public @NotNull LivingEntity getEntity() { return handle.getEntity(); }
    public @NotNull InfernalMobHandle getHandle() { return handle; }
    public @NotNull Player getKiller() { return killer; }
    public int getLevel() { return handle.getLevel(); }
    public @NotNull String getLootId() { return lootId; }
    public @NotNull BigDecimal getOriginalNaturalChance() { return originalNaturalChance; }
    public @NotNull BigDecimal getNaturalChance() { return naturalChance; }

    /** 自动限制到 0 至 1。监听器可基于当前值计算，以叠加已有修饰。 */
    public void setNaturalChance(@NotNull BigDecimal naturalChance) {
        this.naturalChance = Objects.requireNonNull(naturalChance, "自然掉率不能为空")
                .max(BigDecimal.ZERO).min(BigDecimal.ONE);
    }

    @Override
    public @NotNull HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
