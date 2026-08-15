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
 * 重甲（armoured）词条装配生效事件。
 * 外部插件可直接修改或替换本次即将装备到四个护甲槽位的物品。
 *
 * <p>四个槽位字段只影响正常的装备分支；若本体因实体不属于 Mob 而进入抗性效果兜底分支，
 * 对这些字段的修改不会生效。实际装备的物品均沿用本词条的零掉落率规则。
 */
public class InfernalMobArmouredEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;

    public InfernalMobArmouredEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                    @Nullable ItemStack helmet, @Nullable ItemStack chestplate,
                                    @Nullable ItemStack leggings, @Nullable ItemStack boots) {
        super("armoured", SkillType.STAT, mob, target, handle, level);
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.leggings = leggings;
        this.boots = boots;
    }

    /** 本次即将装备到头部槽位的物品；返回的物品可直接修改，null 表示清空槽位。 */
    @Nullable
    public ItemStack getHelmet() {
        return helmet;
    }

    /** 替换本次即将装备到头部槽位的物品；null 表示清空槽位。 */
    public void setHelmet(@Nullable ItemStack helmet) {
        this.helmet = helmet;
    }

    /** 本次即将装备到胸甲槽位的物品；返回的物品可直接修改，null 表示清空槽位。 */
    @Nullable
    public ItemStack getChestplate() {
        return chestplate;
    }

    /** 替换本次即将装备到胸甲槽位的物品；null 表示清空槽位。 */
    public void setChestplate(@Nullable ItemStack chestplate) {
        this.chestplate = chestplate;
    }

    /** 本次即将装备到护腿槽位的物品；返回的物品可直接修改，null 表示清空槽位。 */
    @Nullable
    public ItemStack getLeggings() {
        return leggings;
    }

    /** 替换本次即将装备到护腿槽位的物品；null 表示清空槽位。 */
    public void setLeggings(@Nullable ItemStack leggings) {
        this.leggings = leggings;
    }

    /** 本次即将装备到脚部槽位的物品；返回的物品可直接修改，null 表示清空槽位。 */
    @Nullable
    public ItemStack getBoots() {
        return boots;
    }

    /** 替换本次即将装备到脚部槽位的物品；null 表示清空槽位。 */
    public void setBoots(@Nullable ItemStack boots) {
        this.boots = boots;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
