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

/** thief 内置条件检查通过后、实际转移物品前的夺取尝试事件。 */
public final class InfernalMobThiefStealAttemptEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final Player player;
    private final Allay courier;
    private ItemStack itemStack;
    private Location dropLocation;
    private int cooldownTicks;

    public InfernalMobThiefStealAttemptEvent(LivingEntity mob, InfernalMobHandle handle, int level,
                                             Player player, Allay courier, ItemStack itemStack,
                                             Location dropLocation, int cooldownTicks) {
        super("thief", SkillType.DUAL, mob, Objects.requireNonNull(player, "player"), handle, level);
        this.player = player;
        this.courier = Objects.requireNonNull(courier, "courier");
        this.itemStack = Objects.requireNonNull(itemStack, "itemStack").clone();
        this.dropLocation = Objects.requireNonNull(dropLocation, "dropLocation").clone();
        this.cooldownTicks = Math.max(0, cooldownTicks);
    }

    @NotNull public Player getPlayer() { return player; }
    /** 本次尝试夺取物品的悦灵实体。 */
    @NotNull public Allay getCourier() { return courier; }
    /** 待夺取物品；监听器可替换，空物品会使本次尝试失败。 */
    @NotNull public ItemStack getItemStack() { return itemStack.clone(); }
    public void setItemStack(@NotNull ItemStack itemStack) {
        this.itemStack = Objects.requireNonNull(itemStack, "itemStack").clone();
    }
    @NotNull public Location getDropLocation() { return dropLocation.clone(); }
    public void setDropLocation(@NotNull Location dropLocation) {
        this.dropLocation = Objects.requireNonNull(dropLocation, "dropLocation").clone();
    }
    public int getCooldownTicks() { return cooldownTicks; }
    public void setCooldownTicks(int cooldownTicks) { this.cooldownTicks = Math.max(0, cooldownTicks); }

    @Override public @NotNull HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
