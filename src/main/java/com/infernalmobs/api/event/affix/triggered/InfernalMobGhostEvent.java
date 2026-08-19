package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

/**
 * 亡魂（ghost）词条真正触发事件（怪物死亡时召唤幽灵僵尸）。
 * 外部插件可修改本次召唤的位置、等级、生命、漂浮速度、装备及固定词条组合。
 */
public class InfernalMobGhostEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private Location spawnLocation;
    private int summonLevel;
    private double maxHealth;
    private double floatSpeed;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack mainHand;
    private List<String> affixIds;

    public InfernalMobGhostEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                 @NotNull Location spawnLocation, int summonLevel,
                                 double maxHealth, double floatSpeed,
                                 @Nullable ItemStack helmet, @Nullable ItemStack chestplate,
                                 @Nullable ItemStack mainHand, @NotNull List<String> affixIds) {
        super("ghost", SkillType.DEATH, mob, target, handle, level);
        setSpawnLocation(spawnLocation);
        setSummonLevel(summonLevel);
        setMaxHealth(maxHealth);
        setFloatSpeed(floatSpeed);
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.mainHand = mainHand;
        setAffixIds(affixIds);
    }

    /** 本次幽灵僵尸的生成位置（返回副本）。 */
    @NotNull
    public Location getSpawnLocation() {
        return spawnLocation.clone();
    }

    /** 修改本次幽灵僵尸的生成位置（保存副本）。 */
    public void setSpawnLocation(@NotNull Location spawnLocation) {
        this.spawnLocation = Objects.requireNonNull(spawnLocation, "spawnLocation").clone();
    }

    /** 本次幽灵僵尸炒鸡化时使用的等级。 */
    public int getSummonLevel() {
        return summonLevel;
    }

    /** 修改炒鸡化等级（最低为 1）。 */
    public void setSummonLevel(int summonLevel) {
        this.summonLevel = Math.max(1, summonLevel);
    }

    /** 本次幽灵僵尸最终使用的基础最大生命值。 */
    public double getMaxHealth() {
        return maxHealth;
    }

    /** 修改基础最大生命值；非有限值或不大于 0 的值按 0.1 处理。 */
    public void setMaxHealth(double maxHealth) {
        this.maxHealth = Double.isFinite(maxHealth) && maxHealth > 0.0 ? maxHealth : 0.1;
    }

    /** 每 2 tick 沿幽灵僵尸朝向重新施加的漂浮速度。 */
    public double getFloatSpeed() {
        return floatSpeed;
    }

    /** 修改漂浮速度；非有限值或负数按 0 处理，0 表示不启动漂浮移动任务。 */
    public void setFloatSpeed(double floatSpeed) {
        this.floatSpeed = Double.isFinite(floatSpeed) ? Math.max(0.0, floatSpeed) : 0.0;
    }

    /** 本次即将装备到头部槽位的物品；返回值可直接修改，null 表示清空槽位。 */
    @Nullable
    public ItemStack getHelmet() {
        return helmet;
    }

    /** 替换本次即将装备到头部槽位的物品；null 表示清空槽位。 */
    public void setHelmet(@Nullable ItemStack helmet) {
        this.helmet = helmet;
    }

    /** 本次即将装备到胸甲槽位的物品；返回值可直接修改，null 表示清空槽位。 */
    @Nullable
    public ItemStack getChestplate() {
        return chestplate;
    }

    /** 替换本次即将装备到胸甲槽位的物品；null 表示清空槽位。 */
    public void setChestplate(@Nullable ItemStack chestplate) {
        this.chestplate = chestplate;
    }

    /** 本次即将装备到主手槽位的物品；返回值可直接修改，null 表示空手。 */
    @Nullable
    public ItemStack getMainHand() {
        return mainHand;
    }

    /** 替换本次即将装备到主手槽位的物品；null 表示空手。 */
    public void setMainHand(@Nullable ItemStack mainHand) {
        this.mainHand = mainHand;
    }

    /**
     * 本次幽灵僵尸炒鸡化时请求的固定词条 ID 列表。
     * 返回不可变列表；空列表表示仍生成幽灵僵尸，但不进行炒鸡化。
     */
    @NotNull
    public List<String> getAffixIds() {
        return affixIds;
    }

    /** 替换固定词条 ID 列表；无效或未注册的 ID 会在炒鸡化时被忽略。 */
    public void setAffixIds(@NotNull List<String> affixIds) {
        this.affixIds = List.copyOf(Objects.requireNonNull(affixIds, "affixIds"));
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
