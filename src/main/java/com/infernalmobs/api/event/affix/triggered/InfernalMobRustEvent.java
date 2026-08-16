package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 锈蚀（rust）词条真正触发事件。
 * 外部插件可读取待锈蚀的主手物品快照，并修改本次请求的标准耐久损耗量。
 */
public class InfernalMobRustEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final ItemStack itemStack;
    private int damageAmount;

    public InfernalMobRustEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                @NotNull ItemStack itemStack, int damageAmount) {
        super("rust", SkillType.PASSIVE, mob, target, handle, level);
        this.itemStack = Objects.requireNonNull(itemStack, "itemStack").clone();
        setDamageAmount(damageAmount);
    }

    /** 触发时待锈蚀的主手物品快照；修改返回值不会替换或修改玩家物品。 */
    @NotNull
    public ItemStack getItemStack() {
        return itemStack.clone();
    }

    /** 本次请求交给标准物品耐久流程处理的损耗量。 */
    public int getDamageAmount() {
        return damageAmount;
    }

    /** 修改请求的耐久损耗量（负数按 0 处理）。 */
    public void setDamageAmount(int damageAmount) {
        this.damageAmount = Math.max(0, damageAmount);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
