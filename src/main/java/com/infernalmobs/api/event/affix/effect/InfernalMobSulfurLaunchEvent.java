package com.infernalmobs.api.event.affix.effect;

import com.infernalmobs.api.InfernalMobHandle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 硫泉（sulfur）喷发时的逐玩家顶起事件。
 *
 * <p>该事件在硫泉完成预警、准备为范围内某一名玩家施加竖直速度时触发，
 * 因而不属于“词条成功触发”事件。取消只会阻止该玩家在本次喷发中被顶起，
 * 不会取消已经发生的 sulfur 触发，也不会回滚其冷却。
 */
public class InfernalMobSulfurLaunchEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final LivingEntity mob;
    private final Player player;
    private final InfernalMobHandle handle;
    private final int level;
    private double upward;
    private boolean cancelled = false;

    public InfernalMobSulfurLaunchEvent(LivingEntity mob, Player player, InfernalMobHandle handle, int level,
                                        double upward) {
        this.mob = mob;
        this.player = player;
        this.handle = handle;
        this.level = level;
        this.upward = Math.max(0.0, upward);
    }

    /** 产生本次硫泉的炒鸡怪实体。 */
    @NotNull
    public LivingEntity getMob() {
        return mob;
    }

    /** 即将被顶起的玩家。 */
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

    /** 本次准备施加的竖直速度。 */
    public double getUpward() {
        return upward;
    }

    /** 修改本次准备施加的竖直速度（负数按 0 处理）。 */
    public void setUpward(double upward) {
        this.upward = Math.max(0.0, upward);
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
