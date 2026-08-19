package com.infernalmobs.api.event.affix;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 词条尝试事件：在非 {@link SkillType#STAT} 词条通过内部资格检查、
 * 即将进行技能条件与概率判定前触发。
 *
 * <p>本事件只负责控制“是否允许尝试本次触发”，不承载技能效果参数。
 * 取消事件后，本次尝试会立即结束：不再执行后续条件与概率判定，也不会进入新的冷却。
 * 如需读取或修改真正生效的参数，应监听对应的 {@link InfernalAffixTriggeredEvent} 子类。
 */
public class InfernalAffixAttemptEvent extends InfernalAffixEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public InfernalAffixAttemptEvent(String affixId, SkillType skillType, LivingEntity mob,
                                     LivingEntity target, InfernalMobHandle handle, int level) {
        super(affixId, skillType, mob, target, handle, level);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
