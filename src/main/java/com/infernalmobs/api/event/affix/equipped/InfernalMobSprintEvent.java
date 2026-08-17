package com.infernalmobs.api.event.affix.equipped;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixEquippedEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 疾速（sprint）词条装配生效事件。
 * 外部插件可修改本次常驻速度效果的等级。
 */
public class InfernalMobSprintEvent extends InfernalAffixEquippedEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private int amplifier;

    public InfernalMobSprintEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                  int amplifier) {
        super("sprint", SkillType.STAT, mob, target, handle, level);
        setAmplifier(amplifier);
    }

    /** 本次常驻速度效果的 amplifier；0 表示速度 I。 */
    public int getAmplifier() {
        return amplifier;
    }

    /** 修改速度效果的 amplifier（负数按 0 处理）。 */
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
