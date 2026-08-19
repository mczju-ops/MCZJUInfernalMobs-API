package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 吸血（lifesteal）词条真正触发事件。
 * 外部插件可修改本次持续治疗的时长与每 20 tick 治疗量。
 */
public class InfernalMobLifestealEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private int durationTicks;
    private double healPerSecond;

    public InfernalMobLifestealEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                     int durationTicks, double healPerSecond) {
        super("lifesteal", SkillType.PASSIVE, mob, target, handle, level);
        setDurationTicks(durationTicks);
        setHealPerSecond(healPerSecond);
    }

    /** 持续治疗的总时长；实际治疗次数为该值除以 20 后向下取整。 */
    public int getDurationTicks() {
        return durationTicks;
    }

    /** 修改持续治疗的总时长（负数按 0 处理）。 */
    public void setDurationTicks(int durationTicks) {
        this.durationTicks = Math.max(0, durationTicks);
    }

    /** 每 20 tick 的治疗量。 */
    public double getHealPerSecond() {
        return healPerSecond;
    }

    /** 修改每 20 tick 的治疗量（负数、NaN 和无穷值按 0 处理）。 */
    public void setHealPerSecond(double healPerSecond) {
        this.healPerSecond = Double.isFinite(healPerSecond) ? Math.max(0.0, healPerSecond) : 0.0;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
