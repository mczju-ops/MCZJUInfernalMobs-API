package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 复仇（vengeance）词条真正触发事件。
 * 外部插件可修改本次以原生荆棘伤害结算的反伤值。
 */
public class InfernalMobVengeanceEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private double damage;

    public InfernalMobVengeanceEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                     double damage) {
        super("vengeance", SkillType.PASSIVE, mob, target, handle, level);
        setDamage(damage);
    }

    /** 本次即将交给 Bukkit/Paper 继续结算的荆棘基础伤害。 */
    public double getDamage() {
        return damage;
    }

    /** 修改本次基础伤害（负数、NaN 和无穷值按 0 处理）。 */
    public void setDamage(double damage) {
        this.damage = Double.isFinite(damage) ? Math.max(0.0, damage) : 0.0;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
