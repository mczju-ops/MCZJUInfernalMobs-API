package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * 缴械触发事件（thief 词条）：在炒鸡怪真正缴械玩家主手物品、物品即将掉落时触发（chance 判定通过之后）。
 *
 * <p>外部插件可：
 * <ul>
 *   <li>{@link #getPlayer()} / {@link #getMob()} / {@link #getItemStack()} — 获取玩家、炒鸡怪、被缴械的物品；</li>
 *   <li>{@link #setDropLocation(Location)} — 修改物品掉落位置；</li>
 *   <li>{@link #setCooldownTicks(int)} — 设置该怪 thief 词条的最终冷却；</li>
 *   <li>{@link #setCancelled(boolean)} — 取消本次缴械（物品不掉落）。</li>
 * </ul>
 */
public class InfernalMobThiefEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final ItemStack itemStack;
    private Location dropLocation;
    private int cooldownTicks;

    public InfernalMobThiefEvent(LivingEntity mob, InfernalMobHandle handle, int level,
                                 Player player, ItemStack itemStack,
                                 Location dropLocation, int cooldownTicks) {
        super("thief", SkillType.DUAL, mob, player, handle, level);
        this.itemStack = itemStack;
        this.dropLocation = dropLocation;
        this.cooldownTicks = Math.max(0, cooldownTicks);
    }

    /** 被缴械的玩家。 */
    @NotNull
    public Player getPlayer() {
        return (Player) getTarget();
    }

    /** 被缴械的物品（即将掉落的 ItemStack）。 */
    @NotNull
    public ItemStack getItemStack() {
        return itemStack;
    }

    /** 物品掉落位置。 */
    @NotNull
    public Location getDropLocation() {
        return dropLocation;
    }

    /** 修改物品掉落位置。 */
    public void setDropLocation(@NotNull Location dropLocation) {
        this.dropLocation = dropLocation;
    }

    /** 该怪 thief 词条的最终冷却（tick）。 */
    public int getCooldownTicks() {
        return cooldownTicks;
    }

    /** 设置该怪 thief 词条的最终冷却（tick，负数按 0 处理）。 */
    public void setCooldownTicks(int cooldownTicks) {
        this.cooldownTicks = Math.max(0, cooldownTicks);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
