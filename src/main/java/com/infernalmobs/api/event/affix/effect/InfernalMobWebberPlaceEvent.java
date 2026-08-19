package com.infernalmobs.api.event.affix.effect;

import com.infernalmobs.api.InfernalMobHandle;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 织网（webber）词条准备将某个空气方块替换为蛛网时的逐方块事件。
 *
 * <p>取消只会阻止当前方块，不会影响其他方块，也不会回滚已经发生的
 * webber 触发、冷却或巨型网球的一次性机会。
 */
public class InfernalMobWebberPlaceEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final LivingEntity mob;
    private final Player player;
    private final Block block;
    private final InfernalMobHandle handle;
    private final int level;
    private final boolean giantSphere;
    private boolean cancelled;

    public InfernalMobWebberPlaceEvent(LivingEntity mob, Player player, Block block,
                                       InfernalMobHandle handle, int level, boolean giantSphere) {
        this.mob = mob;
        this.player = player;
        this.block = block;
        this.handle = handle;
        this.level = level;
        this.giantSphere = giantSphere;
    }

    /** 产生本次蛛网的炒鸡怪实体。 */
    @NotNull
    public LivingEntity getMob() {
        return mob;
    }

    /** 本次织网效果所针对的玩家。 */
    @NotNull
    public Player getPlayer() {
        return player;
    }

    /** 即将被替换为蛛网的空气方块。 */
    @NotNull
    public Block getBlock() {
        return block;
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

    /** 当前方块是否属于巨型空心网球变体。 */
    public boolean isGiantSphere() {
        return giantSphere;
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
