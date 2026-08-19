package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 冰冻（refrigerate）词条真正触发事件。
 * 外部插件可修改本次要求目标处于完全冻结状态的持续 tick 数。
 */
public class InfernalMobRefrigerateEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private int freezeTicks;

    public InfernalMobRefrigerateEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                       int freezeTicks) {
        super("refrigerate", SkillType.DUAL, mob, target, handle, level);
        setFreezeTicks(freezeTicks);
    }

    /** 本次要求目标处于完全冻结状态的持续 tick 数；0 表示不改变当前冻结状态。 */
    public int getFreezeTicks() {
        return freezeTicks;
    }

    /** 修改完全冻结状态的持续 tick 数（负数按 0 处理）。 */
    public void setFreezeTicks(int freezeTicks) {
        this.freezeTicks = Math.max(0, freezeTicks);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
