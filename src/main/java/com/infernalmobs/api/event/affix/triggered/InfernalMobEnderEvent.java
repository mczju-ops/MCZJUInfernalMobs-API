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
 * 末影（ender）词条真正触发事件。
 * 外部插件可修改怪物本次传送的最终目的地。
 */
public class InfernalMobEnderEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private Location destination;

    public InfernalMobEnderEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                 @NotNull Location destination) {
        super("ender", SkillType.DUAL, mob, target, handle, level);
        setDestination(destination);
    }

    /** 怪物本次传送的最终目的地。 */
    @NotNull
    public Location getDestination() {
        return destination;
    }

    /** 修改怪物本次传送的最终目的地。 */
    public void setDestination(@NotNull Location destination) {
        this.destination = Objects.requireNonNull(destination, "destination");
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
