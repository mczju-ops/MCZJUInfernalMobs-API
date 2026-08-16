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
 * 织网（webber）词条真正触发事件。
 * 外部插件可修改本次普通蛛网或巨型空心网球的位置与形态参数。
 * 当 {@link #isGiantSphere()} 为 true 时，巨型变体的一次性机会已经消费，取消事件不会返还。
 */
public class InfernalMobWebberEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final boolean giantSphere;
    private Location center;
    private int lifetimeTicks;
    private int radius;
    private double thickness;

    public InfernalMobWebberEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                  boolean giantSphere, @NotNull Location center, int lifetimeTicks,
                                  int radius, double thickness) {
        super("webber", SkillType.DUAL, mob, target, handle, level);
        this.giantSphere = giantSphere;
        setCenter(center);
        setLifetimeTicks(lifetimeTicks);
        setRadius(radius);
        setThickness(thickness);
    }

    /** 本次是否为蜘蛛专属的巨型空心网球变体。 */
    public boolean isGiantSphere() {
        return giantSphere;
    }

    /** 普通蛛网的方块位置，或巨型网球的中心位置（返回副本）。 */
    @NotNull
    public Location getCenter() {
        return center.clone();
    }

    /** 修改普通蛛网的方块位置或巨型网球中心（保存副本）。 */
    public void setCenter(@NotNull Location center) {
        this.center = Objects.requireNonNull(center, "center").clone();
    }

    /** 本次蛛网的自动清理延迟（tick）；0 表示不自动清理。 */
    public int getLifetimeTicks() {
        return lifetimeTicks;
    }

    /** 修改自动清理延迟（tick；负数按 0 处理）。 */
    public void setLifetimeTicks(int lifetimeTicks) {
        this.lifetimeTicks = Math.max(0, lifetimeTicks);
    }

    /** 巨型网球半径；普通蛛网变体忽略该值。 */
    public int getRadius() {
        return radius;
    }

    /** 修改巨型网球半径（负数按 0 处理）。 */
    public void setRadius(int radius) {
        this.radius = Math.max(0, radius);
    }

    /** 巨型网球壳层厚度；普通蛛网变体忽略该值。 */
    public double getThickness() {
        return thickness;
    }

    /** 修改巨型网球壳层厚度（负数、NaN 和无穷值按 0 处理）。 */
    public void setThickness(double thickness) {
        this.thickness = Double.isFinite(thickness) ? Math.max(0.0, thickness) : 0.0;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
