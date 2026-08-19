package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 守护者之怒（wardenwrath）词条真正触发事件。
 * 外部插件可修改本次经过距离衰减后的伤害与击退参数。
 */
public class InfernalMobWardenWrathEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private double damage;
    private double knockbackHorizontal;
    private double knockbackVertical;

    public InfernalMobWardenWrathEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                       double damage, double knockbackHorizontal, double knockbackVertical) {
        super("wardenwrath", SkillType.PASSIVE, mob, target, handle, level);
        setDamage(damage);
        setKnockbackHorizontal(knockbackHorizontal);
        setKnockbackVertical(knockbackVertical);
    }

    /** 本次即将交给 Bukkit/Paper 继续结算的基础伤害。 */
    public double getDamage() {
        return damage;
    }

    /** 修改本次基础伤害（负数、NaN 和无穷值按 0 处理）。 */
    public void setDamage(double damage) {
        this.damage = sanitizeNonNegative(damage);
    }

    /** 本次使用 Paper 标准击退接口施加的水平力度。 */
    public double getKnockbackHorizontal() {
        return knockbackHorizontal;
    }

    /** 修改本次水平击退力度（负数、NaN 和无穷值按 0 处理）。 */
    public void setKnockbackHorizontal(double knockbackHorizontal) {
        this.knockbackHorizontal = sanitizeNonNegative(knockbackHorizontal);
    }

    /** 本次击退要求的最低竖直速度。 */
    public double getKnockbackVertical() {
        return knockbackVertical;
    }

    /** 修改本次最低竖直速度（负数、NaN 和无穷值按 0 处理）。 */
    public void setKnockbackVertical(double knockbackVertical) {
        this.knockbackVertical = sanitizeNonNegative(knockbackVertical);
    }

    private static double sanitizeNonNegative(double value) {
        return Double.isFinite(value) ? Math.max(0.0, value) : 0.0;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
