package com.infernalmobs.api.event;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/** 抛投（tosser）词条真正触发事件。 */
public class InfernalMobTosserEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public InfernalMobTosserEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level) {
        super("tosser", SkillType.RANGE, mob, target, handle, level);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
