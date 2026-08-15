package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;

/**
 * 致盲（blinding）词条真正触发事件。
 * 外部插件可修改本次失明效果的持续时间与效果等级。
 */
public class InfernalMobBlindingEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private int durationTicks;
    private int amplifier;

    public InfernalMobBlindingEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                    int durationTicks, int amplifier) {
        super("blinding", SkillType.PASSIVE, mob, target, handle, level);
        setDurationTicks(durationTicks);
        setAmplifier(amplifier);
    }

    /** 本次失明效果的持续时间（tick）；-1 表示无限时长。 */
    public int getDurationTicks() {
        return durationTicks;
    }

    /** 修改持续时间（tick）；小于 -1 的值按 -1 处理。 */
    public void setDurationTicks(int durationTicks) {
        this.durationTicks = Math.max(PotionEffect.INFINITE_DURATION, durationTicks);
    }

    /** 本次失明效果的 amplifier；0 表示 I 级。 */
    public int getAmplifier() {
        return amplifier;
    }

    /** 修改本次失明效果的 amplifier（负数按 0 处理）。 */
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
