package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 风暴（storm）词条真正触发事件。
 * 外部插件可修改本次落雷位置、逐受害者基础伤害，以及是否仅生成视觉闪电。
 */
public class InfernalMobStormEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private Location strikeLocation;
    private double damage;
    private boolean effectOnly;

    public InfernalMobStormEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                 @NotNull Location strikeLocation, double damage, boolean effectOnly) {
        super("storm", SkillType.DUAL, mob, target, handle, level);
        setStrikeLocation(strikeLocation);
        setDamage(damage);
        setEffectOnly(effectOnly);
    }

    /** 本次闪电的落点。 */
    @NotNull
    public Location getStrikeLocation() {
        return strikeLocation;
    }

    /** 修改本次闪电的落点。 */
    public void setStrikeLocation(@NotNull Location strikeLocation) {
        this.strikeLocation = Objects.requireNonNull(strikeLocation, "strikeLocation");
    }

    /** 真实闪电对每名受害者使用的基础伤害；视觉闪电会忽略该值。 */
    public double getDamage() {
        return damage;
    }

    /** 修改逐受害者基础伤害（负数、NaN 和无穷值按 0 处理）。 */
    public void setDamage(double damage) {
        this.damage = Double.isFinite(damage) ? Math.max(0.0, damage) : 0.0;
    }

    /** 是否仅生成无伤害、无点火和无生物转化的视觉闪电。 */
    public boolean isEffectOnly() {
        return effectOnly;
    }

    /** 修改本次是否仅生成视觉闪电。 */
    public void setEffectOnly(boolean effectOnly) {
        this.effectOnly = effectOnly;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
