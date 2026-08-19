package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 狂暴（berserk）词条真正触发事件。
 * 外部插件可修改本次怪物自伤与追加到原始攻击中的伤害。
 */
public class InfernalMobBerserkEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private double selfDamage;
    private double bonusDamage;

    public InfernalMobBerserkEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                   double selfDamage, double bonusDamage) {
        super("berserk", SkillType.ACTIVE, mob, target, handle, level);
        setSelfDamage(selfDamage);
        setBonusDamage(bonusDamage);
    }

    /** 本次直接从怪物当前生命值扣除的数值；该自伤不经过普通伤害事件。 */
    public double getSelfDamage() {
        return selfDamage;
    }

    /** 修改本次怪物自伤（负数、NaN 和无穷值按 0 处理）。 */
    public void setSelfDamage(double selfDamage) {
        this.selfDamage = Double.isFinite(selfDamage) ? Math.max(0.0, selfDamage) : 0.0;
    }

    /** 本次追加到原始攻击、并由原伤害事件统一结算的伤害。 */
    public double getBonusDamage() {
        return bonusDamage;
    }

    /** 修改本次追加伤害（负数、NaN 和无穷值按 0 处理）。 */
    public void setBonusDamage(double bonusDamage) {
        this.bonusDamage = Double.isFinite(bonusDamage) ? Math.max(0.0, bonusDamage) : 0.0;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
