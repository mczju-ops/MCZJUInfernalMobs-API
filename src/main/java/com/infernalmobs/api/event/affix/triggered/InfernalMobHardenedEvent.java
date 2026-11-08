package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * 硬化次数盾触发事件：最终伤害超过阈值、即将消耗一次盾并限制基础伤害时广播。
 * 取消仍消耗一次盾，但阻止硬化减伤、粒子和音效，不会取消原伤害事件。
 * target 为攻击者（投射物解析为活体射手），环境伤害等没有活体来源时为 null。
 */
public final class InfernalMobHardenedEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private final EntityDamageEvent damageEvent;
    private final double originalFinalDamage;
    private final int remainingCharges;
    private double damageLimit;

    public InfernalMobHardenedEvent(@NotNull LivingEntity mob, @Nullable LivingEntity target,
                                   @NotNull InfernalMobHandle handle, int level,
                                   @NotNull EntityDamageEvent damageEvent, int remainingCharges,
                                   double damageLimit) {
        super("hardened", SkillType.STAT, mob, target, handle, level);
        this.damageEvent = Objects.requireNonNull(damageEvent, "damageEvent");
        this.originalFinalDamage = damageEvent.getFinalDamage();
        this.remainingCharges = remainingCharges;
        setDamageLimit(damageLimit);
    }

    /** 原伤害事件，可查询来源、原因及伤害明细；不是副本。 */
    @NotNull
    public EntityDamageEvent getDamageEvent() { return damageEvent; }

    /** 本事件广播前的最终伤害快照。 */
    public double getOriginalFinalDamage() { return originalFinalDamage; }

    /** 本次消耗前的剩余次数快照。 */
    public int getRemainingCharges() { return remainingCharges; }

    /** 本次基础伤害上限；护甲等后续减免仍会生效。 */
    public double getDamageLimit() { return damageLimit; }

    /** 修改基础伤害上限；负数和非有限值按 0 处理，不会提高原基础伤害。 */
    public void setDamageLimit(double damageLimit) {
        this.damageLimit = Double.isFinite(damageLimit) ? Math.max(0.0, damageLimit) : 0.0;
    }

    @Override
    public @NotNull HandlerList getHandlers() { return HANDLERS; }

    public static HandlerList getHandlerList() { return HANDLERS; }
}
