package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 恶魂（ghastly）词条真正触发事件。
 * 外部插件可修改本次火球的发射参数、直击效果和最大存在时间。
 */
public class InfernalMobGhastlyEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private Location spawnLocation;
    private Vector velocity;
    private double directDamage;
    private int fireTicks;
    private float explosionPower;
    private int lifetimeTicks;

    public InfernalMobGhastlyEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                   @NotNull Location spawnLocation, @NotNull Vector velocity,
                                   double directDamage, int fireTicks, float explosionPower, int lifetimeTicks) {
        super("ghastly", SkillType.RANGE, mob, target, handle, level);
        setSpawnLocation(spawnLocation);
        setVelocity(velocity);
        setDirectDamage(directDamage);
        setFireTicks(fireTicks);
        setExplosionPower(explosionPower);
        setLifetimeTicks(lifetimeTicks);
    }

    /** 本次火球的生成位置。 */
    @NotNull
    public Location getSpawnLocation() {
        return spawnLocation;
    }

    /** 修改本次火球的生成位置。 */
    public void setSpawnLocation(@NotNull Location spawnLocation) {
        this.spawnLocation = Objects.requireNonNull(spawnLocation, "spawnLocation");
    }

    /** 本次火球的初始速度向量。 */
    @NotNull
    public Vector getVelocity() {
        return velocity.clone();
    }

    /** 修改初始速度；任一分量为 NaN 或无穷值时按零向量处理。 */
    public void setVelocity(@NotNull Vector velocity) {
        Objects.requireNonNull(velocity, "velocity");
        this.velocity = Double.isFinite(velocity.getX())
                && Double.isFinite(velocity.getY())
                && Double.isFinite(velocity.getZ())
                ? velocity.clone() : new Vector();
    }

    /** 火球直接命中实体时使用的基础伤害。 */
    public double getDirectDamage() {
        return directDamage;
    }

    /** 修改直接命中的基础伤害（负数、NaN 和无穷值按 0 处理）。 */
    public void setDirectDamage(double directDamage) {
        this.directDamage = Double.isFinite(directDamage) ? Math.max(0.0, directDamage) : 0.0;
    }

    /** 火球直接命中实体后施加的燃烧时间（tick）。 */
    public int getFireTicks() {
        return fireTicks;
    }

    /** 修改燃烧时间（tick，负数按 0 处理）。 */
    public void setFireTicks(int fireTicks) {
        this.fireTicks = Math.max(0, fireTicks);
    }

    /** 火球爆炸的基础威力。 */
    public float getExplosionPower() {
        return explosionPower;
    }

    /** 修改爆炸威力（负数、NaN 和无穷值按 0 处理）。 */
    public void setExplosionPower(float explosionPower) {
        this.explosionPower = Float.isFinite(explosionPower) ? Math.max(0.0f, explosionPower) : 0.0f;
    }

    /** 火球最多存在的时间（tick）；0 表示不生成火球。 */
    public int getLifetimeTicks() {
        return lifetimeTicks;
    }

    /** 修改最大存在时间（tick，负数按 0 处理）。 */
    public void setLifetimeTicks(int lifetimeTicks) {
        this.lifetimeTicks = Math.max(0, lifetimeTicks);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
