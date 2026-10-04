package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.Location;
import org.bukkit.entity.Allay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

/**
 * thief 悦灵命中玩家后的夺取前事件。
 *
 * <p>事件取消、保留空手、创造模式、抗性物品或物品在事件期间发生变化，都会使本次夺取失败，
 * 但悦灵仍会按正常流程返程。监听器可以替换物品、修改掉落位置和最终冷却。</p>
 */
public final class InfernalMobThiefHitEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final Allay courier;
    private ItemStack itemStack;
    private Location dropLocation;
    private int cooldownTicks;

    public InfernalMobThiefHitEvent(LivingEntity mob, InfernalMobHandle handle, int level,
                                    Player player, Allay courier, ItemStack itemStack,
                                    Location dropLocation, int cooldownTicks) {
        super("thief", SkillType.DUAL, mob, player, handle, level);
        this.player = Objects.requireNonNull(player, "player");
        this.courier = Objects.requireNonNull(courier, "courier");
        this.itemStack = Objects.requireNonNull(itemStack, "itemStack").clone();
        this.dropLocation = Objects.requireNonNull(dropLocation, "dropLocation").clone();
        this.cooldownTicks = Math.max(0, cooldownTicks);
    }

    @NotNull
    public Player getPlayer() {
        return player;
    }

    /** 本次悦灵实体。 */
    @NotNull
    public Allay getCourier() {
        return courier;
    }

    /** 尝试夺取的物品；允许替换为其他物品，空物品表示本次夺取失败。 */
    @NotNull
    public ItemStack getItemStack() {
        return itemStack.clone();
    }

    public void setItemStack(@NotNull ItemStack itemStack) {
        this.itemStack = Objects.requireNonNull(itemStack, "itemStack").clone();
    }

    @NotNull
    public Location getDropLocation() {
        return dropLocation.clone();
    }

    public void setDropLocation(@NotNull Location dropLocation) {
        this.dropLocation = Objects.requireNonNull(dropLocation, "dropLocation").clone();
    }

    public int getCooldownTicks() {
        return cooldownTicks;
    }

    public void setCooldownTicks(int cooldownTicks) {
        this.cooldownTicks = Math.max(0, cooldownTicks);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
