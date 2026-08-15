package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 牵引（tosser）词条真正触发事件。
 * 外部插件可修改本次指向怪物的水平力度与竖直分量。
 */
public class InfernalMobTosserEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private double force;
    private double upward;

    public InfernalMobTosserEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                  double force, double upward) {
        super("tosser", SkillType.RANGE, mob, target, handle, level);
        this.force = Math.max(0.0, force);
        this.upward = Math.max(0.0, upward);
    }

    /** 本次指向怪物的水平力度。 */
    public double getForce() {
        return force;
    }

    /** 修改本次指向怪物的水平力度（负数按 0 处理）。 */
    public void setForce(double force) {
        this.force = Math.max(0.0, force);
    }

    /** 本次牵引的竖直分量。 */
    public double getUpward() {
        return upward;
    }

    /** 修改本次牵引的竖直分量（负数按 0 处理）。 */
    public void setUpward(double upward) {
        this.upward = Math.max(0.0, upward);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
