package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/** 剧毒（poisonous）词条真正触发事件。 */
public class InfernalMobPoisonousEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public InfernalMobPoisonousEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level) {
        super("poisonous", SkillType.PASSIVE, mob, target, handle, level);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
