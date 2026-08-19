package com.infernalmobs.api.event.affix.effect;

import com.infernalmobs.api.InfernalMobHandle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 长枪（spear）冲锋阶段对锁定玩家造成真实近战攻击时的单次命中事件。
 *
 * <p>取消会取消对应的原始近战伤害；该次攻击仍会消耗本轮 Spear 唯一的命中机会，
 * 不会回滚已经发生的 spear 触发或冷却。
 */
public class InfernalMobSpearHitEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final LivingEntity mob;
    private final Player player;
    private final InfernalMobHandle handle;
    private final int level;
    private double damage;
    private boolean cancelled = false;

    public InfernalMobSpearHitEvent(LivingEntity mob, Player player, InfernalMobHandle handle,
                                    int level, double damage) {
        this.mob = mob;
        this.player = player;
        this.handle = handle;
        this.level = level;
        setDamage(damage);
    }

    /** 发起本次强化追逐的炒鸡怪实体。 */
    @NotNull
    public LivingEntity getMob() {
        return mob;
    }

    /** 本次受到真实近战攻击的玩家。 */
    @NotNull
    public Player getPlayer() {
        return player;
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

    /** 即将交给 Bukkit/Paper 继续结算的原始近战伤害。 */
    public double getDamage() {
        return damage;
    }

    /** 修改本次原始近战伤害（负数、NaN 和无穷值按 0 处理）。 */
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
