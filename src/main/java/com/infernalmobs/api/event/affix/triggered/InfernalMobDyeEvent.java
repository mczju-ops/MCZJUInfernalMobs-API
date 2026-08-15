package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/** 染色（dye）词条真正触发事件（怪物死亡时抽取染料）。 */
public class InfernalMobDyeEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public InfernalMobDyeEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level) {
        super("dye", SkillType.DEATH, mob, target, handle, level);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
