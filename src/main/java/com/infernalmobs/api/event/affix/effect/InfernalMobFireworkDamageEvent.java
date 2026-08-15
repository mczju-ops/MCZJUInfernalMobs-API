package com.infernalmobs.api.event.affix.effect;

import com.infernalmobs.api.InfernalMobHandle;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 烟花（firework）爆炸时的逐受害者伤害事件。
 *
 * <p>该事件在原版烟花计算出某个实体的基础伤害后、以炒鸡怪为来源重新进行伤害结算前广播。
 * 取消只会阻止当前受害者受到本次伤害，不会影响同次爆炸中的其他实体，
 * 也不会回滚已经发生的 firework 触发或冷却。
 */
public class InfernalMobFireworkDamageEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final LivingEntity mob;
    private final LivingEntity victim;
    private final Firework firework;
    private final InfernalMobHandle handle;
    private final int level;
    private double damage;
    private boolean cancelled = false;

    public InfernalMobFireworkDamageEvent(LivingEntity mob, LivingEntity victim, Firework firework,
                                          InfernalMobHandle handle, int level, double damage) {
        this.mob = mob;
        this.victim = victim;
        this.firework = firework;
        this.handle = handle;
        this.level = level;
        setDamage(damage);
    }

    /** 产生本次烟花的炒鸡怪实体。 */
    @NotNull
    public LivingEntity getMob() {
        return mob;
    }

    /** 本次即将受到伤害的实体。 */
    @NotNull
    public LivingEntity getVictim() {
        return victim;
    }

    /** 产生本次伤害的烟花实体。 */
    @NotNull
    public Firework getFirework() {
        return firework;
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

    /** 即将交给 Bukkit/Paper 重新结算的基础伤害。 */
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
