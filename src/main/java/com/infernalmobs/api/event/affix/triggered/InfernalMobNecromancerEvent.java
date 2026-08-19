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
 * 死灵（necromancer）词条真正触发事件。
 * 外部插件可修改本次凋灵之首的发射参数、爆炸属性和最大存在时间。
 */
public class InfernalMobNecromancerEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private Location spawnLocation;
    private Vector velocity;
    private float explosionPower;
    private boolean charged;
    private int lifetimeTicks;

    public InfernalMobNecromancerEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                       @NotNull Location spawnLocation, @NotNull Vector velocity,
                                       float explosionPower, boolean charged, int lifetimeTicks) {
        super("necromancer", SkillType.RANGE, mob, target, handle, level);
        setSpawnLocation(spawnLocation);
        setVelocity(velocity);
        setExplosionPower(explosionPower);
        setCharged(charged);
        setLifetimeTicks(lifetimeTicks);
    }

    /** 本次凋灵之首的生成位置。 */
    @NotNull
    public Location getSpawnLocation() {
        return spawnLocation;
    }

    /** 修改本次凋灵之首的生成位置。 */
    public void setSpawnLocation(@NotNull Location spawnLocation) {
        this.spawnLocation = Objects.requireNonNull(spawnLocation, "spawnLocation");
    }

    /** 本次凋灵之首的初始速度向量。 */
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

    /** 凋灵之首爆炸的基础威力。 */
    public float getExplosionPower() {
        return explosionPower;
    }

    /** 修改爆炸威力（负数、NaN 和无穷值按 0 处理）。 */
    public void setExplosionPower(float explosionPower) {
        this.explosionPower = Float.isFinite(explosionPower) ? Math.max(0.0f, explosionPower) : 0.0f;
    }

    /** 本次是否生成蓝色的蓄力凋灵之首。 */
    public boolean isCharged() {
        return charged;
    }

    /** 修改本次凋灵之首的蓄力状态。 */
    public void setCharged(boolean charged) {
        this.charged = charged;
    }

    /** 凋灵之首最多存在的时间（tick）；0 表示不生成。 */
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
