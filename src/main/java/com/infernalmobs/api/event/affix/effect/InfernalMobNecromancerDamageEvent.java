package com.infernalmobs.api.event.affix.effect;

import com.infernalmobs.api.InfernalMobHandle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.jetbrains.annotations.NotNull;

/**
 * 死灵（necromancer）凋灵之首造成伤害时的逐受害者事件。
 *
 * <p>直接命中与爆炸范围伤害都会分别广播。取消只会阻止当前受害者的当前伤害，
 * 不会影响其他受害者，也不会回滚已经发生的 necromancer 触发或冷却。
 */
public class InfernalMobNecromancerDamageEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final LivingEntity mob;
    private final LivingEntity victim;
    private final WitherSkull witherSkull;
    private final InfernalMobHandle handle;
    private final int level;
    private final EntityDamageEvent.DamageCause damageCause;
    private double damage;
    private boolean cancelled = false;

    public InfernalMobNecromancerDamageEvent(LivingEntity mob, LivingEntity victim, WitherSkull witherSkull,
                                              InfernalMobHandle handle, int level,
                                              EntityDamageEvent.DamageCause damageCause, double damage) {
        this.mob = mob;
        this.victim = victim;
        this.witherSkull = witherSkull;
        this.handle = handle;
        this.level = level;
        this.damageCause = damageCause;
        setDamage(damage);
    }

    /** 发射本次凋灵之首的炒鸡怪实体。 */
    @NotNull
    public LivingEntity getMob() {
        return mob;
    }

    /** 本次即将受到伤害的实体。 */
    @NotNull
    public LivingEntity getVictim() {
        return victim;
    }

    /** 产生本次伤害的凋灵之首实体。 */
    @NotNull
    public WitherSkull getWitherSkull() {
        return witherSkull;
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

    /** 原生伤害原因，可用于区分直接命中与爆炸范围伤害。 */
    @NotNull
    public EntityDamageEvent.DamageCause getDamageCause() {
        return damageCause;
    }

    /** 即将交给 Bukkit/Paper 继续结算的基础伤害。 */
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
