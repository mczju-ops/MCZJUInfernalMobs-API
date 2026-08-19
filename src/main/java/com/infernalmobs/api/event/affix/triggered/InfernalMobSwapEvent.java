package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 移形（swap）词条真正触发事件。
 * 外部插件可分别修改怪物与玩家本次交换的最终目的地。
 */
public class InfernalMobSwapEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private Location mobDestination;
    private Location playerDestination;

    public InfernalMobSwapEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                @NotNull Location mobDestination, @NotNull Location playerDestination) {
        super("swap", SkillType.PASSIVE, mob, target, handle, level);
        setMobDestination(mobDestination);
        setPlayerDestination(playerDestination);
    }

    /** 怪物本次交换的最终目的地。 */
    @NotNull
    public Location getMobDestination() {
        return mobDestination;
    }

    /** 修改怪物本次交换的最终目的地。 */
    public void setMobDestination(@NotNull Location mobDestination) {
        this.mobDestination = Objects.requireNonNull(mobDestination, "mobDestination");
    }

    /** 玩家本次交换的最终目的地。 */
    @NotNull
    public Location getPlayerDestination() {
        return playerDestination;
    }

    /** 修改玩家本次交换的最终目的地。 */
    public void setPlayerDestination(@NotNull Location playerDestination) {
        this.playerDestination = Objects.requireNonNull(playerDestination, "playerDestination");
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
