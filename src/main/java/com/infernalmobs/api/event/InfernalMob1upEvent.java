package com.infernalmobs.api.event;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 1up（保命）词条真正触发事件：在炒鸡怪血量低于阈值、即将触发回满时触发（仅一次）。
 * 取消本事件可阻止本次保命。
 */
public class InfernalMob1upEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public InfernalMob1upEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level) {
        super("1up", SkillType.STAT, mob, target, handle, level);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
