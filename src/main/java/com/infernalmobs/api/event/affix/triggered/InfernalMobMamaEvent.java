package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 母体（mama）词条真正触发事件。
 * 外部插件可修改本批子怪的数量、类型、生成位置、等级范围与幼体策略。
 */
public class InfernalMobMamaEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private int count;
    private EntityType childType;
    private Location spawnLocation;
    private int childLevelMin;
    private int childLevelMax;
    private boolean baby;
    private double noBabyScale;

    public InfernalMobMamaEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                int count, @NotNull EntityType childType, @NotNull Location spawnLocation,
                                int childLevelMin, int childLevelMax, boolean baby, double noBabyScale) {
        super("mama", SkillType.PASSIVE, mob, target, handle, level);
        setCount(count);
        setChildType(childType);
        setSpawnLocation(spawnLocation);
        setChildLevelRange(childLevelMin, childLevelMax);
        setBaby(baby);
        setNoBabyScale(noBabyScale);
    }

    /** 本批生成的子怪数量；0 表示不生成，但本次词条仍已成功触发。 */
    public int getCount() {
        return count;
    }

    /** 修改本批生成的子怪数量（负数按 0 处理）。 */
    public void setCount(int count) {
        this.count = Math.max(0, count);
    }

    /** 本批子怪的实体类型。 */
    @NotNull
    public EntityType getChildType() {
        return childType;
    }

    /** 修改子怪类型；仅接受可生成的生物实体类型。 */
    public void setChildType(@NotNull EntityType childType) {
        Objects.requireNonNull(childType, "childType");
        Class<?> entityClass = childType.getEntityClass();
        if (!childType.isSpawnable() || entityClass == null
                || !LivingEntity.class.isAssignableFrom(entityClass)) {
            throw new IllegalArgumentException("childType must be a spawnable LivingEntity type");
        }
        this.childType = childType;
    }

    /** 本批子怪共用的生成位置。 */
    @NotNull
    public Location getSpawnLocation() {
        return spawnLocation;
    }

    /** 修改本批子怪共用的生成位置。 */
    public void setSpawnLocation(@NotNull Location spawnLocation) {
        this.spawnLocation = Objects.requireNonNull(spawnLocation, "spawnLocation");
    }

    /** 子怪随机等级下限（含）。 */
    public int getChildLevelMin() {
        return childLevelMin;
    }

    /** 修改子怪随机等级下限（最低为 1；若超过当前上限，则上限同步提高）。 */
    public void setChildLevelMin(int childLevelMin) {
        this.childLevelMin = Math.max(1, childLevelMin);
        this.childLevelMax = Math.max(this.childLevelMin, this.childLevelMax);
    }

    /** 子怪随机等级上限（含）。 */
    public int getChildLevelMax() {
        return childLevelMax;
    }

    /** 修改子怪随机等级上限（不会低于当前下限）。 */
    public void setChildLevelMax(int childLevelMax) {
        this.childLevelMax = Math.max(childLevelMin, childLevelMax);
    }

    /** 同时修改子怪随机等级范围；下限最低为 1，上限不会低于下限。 */
    public void setChildLevelRange(int childLevelMin, int childLevelMax) {
        this.childLevelMin = Math.max(1, childLevelMin);
        this.childLevelMax = Math.max(this.childLevelMin, childLevelMax);
    }

    /** 是否优先将子怪设为原生幼体。 */
    public boolean isBaby() {
        return baby;
    }

    /** 修改本批子怪是否优先使用原生幼体。 */
    public void setBaby(boolean baby) {
        this.baby = baby;
    }

    /** 不支持原生幼体时使用的体型缩放；0 表示不缩放。 */
    public double getNoBabyScale() {
        return noBabyScale;
    }

    /** 修改幼体回退缩放；仅 (0.01, 10] 有效，其他值按 0 处理。 */
    public void setNoBabyScale(double noBabyScale) {
        this.noBabyScale = Double.isFinite(noBabyScale) && noBabyScale > 0.01 && noBabyScale <= 10.0
                ? noBabyScale : 0.0;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
