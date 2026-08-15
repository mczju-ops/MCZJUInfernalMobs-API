package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 潜行（cloaked）词条装配生效事件。
 * 外部插件可修改本次隐身的持续时间，以及用于遮蔽实体外观的头部槽位物品。
 * 非 Mob 实体仍会应用隐身，但不会使用头盔字段；实际装备的头盔沿用本词条的零掉落率规则。
 */
public class InfernalMobCloakedEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private int durationTicks;
    private ItemStack helmet;

    public InfernalMobCloakedEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                   int durationTicks, @Nullable ItemStack helmet) {
        super("cloaked", SkillType.STAT, mob, target, handle, level);
        setDurationTicks(durationTicks);
        this.helmet = helmet;
    }

    /** 本次隐身效果的持续时间（tick）；-1 表示无限时长。 */
    public int getDurationTicks() {
        return durationTicks;
    }

    /** 修改持续时间（tick）；小于 -1 的值按 -1 处理。 */
    public void setDurationTicks(int durationTicks) {
        this.durationTicks = Math.max(PotionEffect.INFINITE_DURATION, durationTicks);
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

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
