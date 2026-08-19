package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 1up（保命）词条真正触发事件：在炒鸡怪血量低于阈值、即将免除本次伤害并回复生命时触发（仅一次）。
 * 外部插件可修改本次回复量；取消本事件可阻止本次保命，但不会返还一次性机会。
 */
public class InfernalMob1upEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private double recoveryAmount;

    public InfernalMob1upEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                               double recoveryAmount) {
        super("1up", SkillType.STAT, mob, target, handle, level);
        setRecoveryAmount(recoveryAmount);
    }

    /** 本次保命免除伤害后额外回复的生命值。 */
    public double getRecoveryAmount() {
        return recoveryAmount;
    }

    /**
     * 修改本次额外回复的生命值。
     * 负数、NaN 和无穷值按 0 处理；实际回复仍受怪物的治疗上限约束。
     */
    public void setRecoveryAmount(double recoveryAmount) {
        this.recoveryAmount = Double.isFinite(recoveryAmount) ? Math.max(0.0, recoveryAmount) : 0.0;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
