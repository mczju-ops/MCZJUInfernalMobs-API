package com.infernalmobs.api.event;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/** 冰冻（refrigerate）词条真正触发事件。 */
public class InfernalMobRefrigerateEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public InfernalMobRefrigerateEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level) {
        super("refrigerate", SkillType.DUAL, mob, target, handle, level);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
