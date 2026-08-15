package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 重力（gravity）词条真正触发事件。
 * 外部插件可修改本次漂浮效果的持续时间与效果等级。
 */
public class InfernalMobGravityEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private int durationTicks;
    private int amplifier;

    public InfernalMobGravityEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                   int durationTicks, int amplifier) {
        super("gravity", SkillType.RANGE, mob, target, handle, level);
        this.durationTicks = Math.max(0, durationTicks);
        this.amplifier = Math.max(0, amplifier);
    }

    /** 本次漂浮效果的持续时间（tick）。 */
    public int getDurationTicks() {
        return durationTicks;
    }

    /** 修改本次漂浮效果的持续时间（tick，负数按 0 处理）。 */
    public void setDurationTicks(int durationTicks) {
        this.durationTicks = Math.max(0, durationTicks);
    }

    /** 本次漂浮效果的 amplifier；0 表示 I 级。 */
    public int getAmplifier() {
        return amplifier;
    }

    /** 修改本次漂浮效果的 amplifier（负数按 0 处理）。 */
    public void setAmplifier(int amplifier) {
        this.amplifier = Math.max(0, amplifier);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
