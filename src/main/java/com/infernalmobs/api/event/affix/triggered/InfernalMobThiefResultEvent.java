package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.skill.SkillType;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Allay;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

/** thief 悦灵完成命中处理后的只读结果通知。 */
public final class InfernalMobThiefResultEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private final LivingEntity mob;
    private final InfernalMobHandle handle;
    private final int level;
    private final Player player;
    private final Allay courier;
    private final ItemStack attemptedItem;
    private final ItemStack stolenItem;
    private final Location dropLocation;
    private final Result result;
    private final FailureReason failureReason;

    public InfernalMobThiefResultEvent(LivingEntity mob, InfernalMobHandle handle, int level,
                                       Player player, Allay courier, ItemStack attemptedItem,
                                       ItemStack stolenItem, Location dropLocation, Result result,
                                       FailureReason failureReason) {
        this.mob = Objects.requireNonNull(mob, "mob");
        this.handle = Objects.requireNonNull(handle, "handle");
        this.level = level;
        this.player = Objects.requireNonNull(player, "player");
        this.courier = Objects.requireNonNull(courier, "courier");
        this.attemptedItem = Objects.requireNonNull(attemptedItem, "attemptedItem").clone();
        this.stolenItem = Objects.requireNonNull(stolenItem, "stolenItem").clone();
        this.dropLocation = Objects.requireNonNull(dropLocation, "dropLocation").clone();
        this.result = Objects.requireNonNull(result, "result");
        this.failureReason = Objects.requireNonNull(failureReason, "failureReason");
    }

    @NotNull public String getAffixId() { return "thief"; }
    @NotNull public SkillType getSkillType() { return SkillType.DUAL; }
    @NotNull public LivingEntity getMob() { return mob; }
    @NotNull public InfernalMobHandle getHandle() { return handle; }
    public int getLevel() { return level; }
    @NotNull public Player getPlayer() { return player; }
    /** 本次夺取处理的词条目标，与 {@link #getPlayer()} 相同。 */
    @NotNull public Player getTarget() { return player; }
    @NotNull public Allay getCourier() { return courier; }
    @NotNull public ItemStack getAttemptedItem() { return attemptedItem.clone(); }
    @NotNull public ItemStack getStolenItem() { return stolenItem.clone(); }
    @NotNull public Location getDropLocation() { return dropLocation.clone(); }
    @NotNull public Result getResult() { return result; }
    @NotNull public FailureReason getFailureReason() { return failureReason; }

    @Override public @NotNull HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }

    /** 本次夺取的最终结果。 */
    public enum Result {
        STOLEN, FAILED
    }

    /** 夺取失败的具体原因；成功时固定为 {@link #NONE}。 */
    public enum FailureReason {
        NONE, CANCELLED, EMPTY_HAND, CREATIVE, RESISTANT_ITEM, ITEM_CHANGED, INVALID_ITEM
    }
}
