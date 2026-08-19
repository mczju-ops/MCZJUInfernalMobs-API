package com.infernalmobs.api.event.affix.effect;

import com.infernalmobs.api.InfernalMobHandle;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 风暴（storm）真实闪电造成伤害时的逐受害者事件。
 *
 * <p>取消只会阻止当前受害者的当前伤害，不会影响其他受害者，
 * 也不会撤销闪电造成的点火、生物转化或已经发生的 storm 触发与冷却。
 */
public class InfernalMobStormDamageEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final LivingEntity mob;
    private final LivingEntity victim;
    private final LightningStrike lightningStrike;
    private final InfernalMobHandle handle;
    private final int level;
    private double damage;
    private boolean cancelled = false;

    public InfernalMobStormDamageEvent(LivingEntity mob, LivingEntity victim, LightningStrike lightningStrike,
                                        InfernalMobHandle handle, int level, double damage) {
        this.mob = mob;
        this.victim = victim;
        this.lightningStrike = lightningStrike;
        this.handle = handle;
        this.level = level;
        setDamage(damage);
    }

    /** 产生本次闪电的炒鸡怪实体。 */
    @NotNull
    public LivingEntity getMob() {
        return mob;
    }

    /** 本次即将受到伤害的实体。 */
    @NotNull
    public LivingEntity getVictim() {
        return victim;
    }

    /** 产生本次伤害的真实闪电实体。 */
    @NotNull
    public LightningStrike getLightningStrike() {
        return lightningStrike;
    }

    /** 炒鸡怪门面句柄（只读）。 */
    @NotNull
    public InfernalMobHandle getHandle() {
        return handle;
    }

    /** 炒鸡怪等级。 */
    public int getLevel() {
        return level;
    }

    /** 即将交给 Bukkit/Paper 继续结算的闪电基础伤害。 */
    public double getDamage() {
        return damage;
    }

    /** 修改本次基础伤害（负数、NaN 和无穷值按 0 处理）。 */
    public void setDamage(double damage) {
        this.damage = Double.isFinite(damage) ? Math.max(0.0, damage) : 0.0;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
