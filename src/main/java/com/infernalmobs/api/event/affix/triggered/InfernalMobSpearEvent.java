package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 长枪（spear）词条真正触发事件。
 * 外部插件可修改本次蓄力、强化追逐、临时武器与命中参数。
 */
public class InfernalMobSpearEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private int chargeTicks;
    private int lungeTicks;
    private int lungeSpeedAmplifier;
    private ItemStack spearItem;
    private double hitRadius;
    private double damage;

    public InfernalMobSpearEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                 int chargeTicks, int lungeTicks, int lungeSpeedAmplifier,
                                 @Nullable ItemStack spearItem, double hitRadius, double damage) {
        super("spear", SkillType.RANGE, mob, target, handle, level);
        setChargeTicks(chargeTicks);
        setLungeTicks(lungeTicks);
        setLungeSpeedAmplifier(lungeSpeedAmplifier);
        this.spearItem = spearItem;
        setHitRadius(hitRadius);
        setDamage(damage);
    }

    /** 强化追逐开始前的蓄力时间（tick）。 */
    public int getChargeTicks() {
        return chargeTicks;
    }

    /** 修改蓄力时间（tick，最低为 1）。 */
    public void setChargeTicks(int chargeTicks) {
        this.chargeTicks = Math.max(1, chargeTicks);
    }

    /** 蓄力完成后的强化追逐时间（tick）。 */
    public int getLungeTicks() {
        return lungeTicks;
    }

    /** 修改强化追逐时间（tick，最低为 1）。 */
    public void setLungeTicks(int lungeTicks) {
        this.lungeTicks = Math.max(1, lungeTicks);
    }

    /** 强化追逐期间施加的速度效果 amplifier。 */
    public int getLungeSpeedAmplifier() {
        return lungeSpeedAmplifier;
    }

    /** 修改速度效果 amplifier（负数按 0 处理）。 */
    public void setLungeSpeedAmplifier(int lungeSpeedAmplifier) {
        this.lungeSpeedAmplifier = Math.max(0, lungeSpeedAmplifier);
    }

    /** 蓄力和强化追逐期间使用的临时主手物品；返回值可直接修改，null 表示空手。 */
    @Nullable
    public ItemStack getSpearItem() {
        return spearItem;
    }

    /** 替换本次临时主手物品；null 表示空手。 */
    public void setSpearItem(@Nullable ItemStack spearItem) {
        this.spearItem = spearItem;
    }

    /** 判定与目标接触的三维距离半径。 */
    public double getHitRadius() {
        return hitRadius;
    }

    /** 修改命中半径（负数、NaN 和无穷值按 0 处理）。 */
    public void setHitRadius(double hitRadius) {
        this.hitRadius = Double.isFinite(hitRadius) ? Math.max(0.0, hitRadius) : 0.0;
    }

    /** 传给实际命中阶段事件的基础伤害。 */
    public double getDamage() {
        return damage;
    }

    /** 修改基础伤害（负数、NaN 和无穷值按 0 处理）。 */
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
