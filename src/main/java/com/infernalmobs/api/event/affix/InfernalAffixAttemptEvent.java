package com.infernalmobs.api.event.affix;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 词条尝试事件：在非 {@link SkillType#STAT} 词条通过内部资格检查、
 * 即将进行技能条件与概率判定前触发。
 *
 * <p>本事件只负责控制“是否允许尝试本次触发”，不承载技能效果参数。
 * 取消事件后，本次尝试会立即结束：不再执行后续条件与概率判定，也不会进入新的冷却。
 * 如需读取或修改真正生效的参数，应监听对应的 {@link InfernalAffixTriggeredEvent} 子类。
 */
public class InfernalAffixAttemptEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();

    private final String affixId;
    private final SkillType skillType;
    private final LivingEntity mob;
    private final LivingEntity target;
    private final InfernalMobHandle handle;
    private final int level;
    private boolean cancelled = false;

    public InfernalAffixAttemptEvent(String affixId, SkillType skillType, LivingEntity mob,
                                     LivingEntity target, InfernalMobHandle handle, int level) {
        this.affixId = affixId;
        this.skillType = skillType;
        this.mob = mob;
        this.target = target;
        this.handle = handle;
        this.level = level;
    }

    /** 参与本次尝试的词条 skillId（如 "gravity"）。 */
    @NotNull
    public String getAffixId() {
        return affixId;
    }

    /** 词条技能类型；本事件不会用于 {@link SkillType#STAT} 词条。 */
    @NotNull
    public SkillType getSkillType() {
        return skillType;
    }

    /** 尝试触发技能的炒鸡怪实体。 */
    @NotNull
    public LivingEntity getMob() {
        return mob;
    }

    /** 本次尝试的交互目标；死亡类技能等场景可能为 null。 */
    @Nullable
    public LivingEntity getTarget() {
        return target;
    }

    /** 炒鸡怪门面句柄（只读）。 */
    @NotNull
    public InfernalMobHandle getHandle() {
        return handle;
    }

    /** 炒鸡怪等级。 */
    public int getLevel() {
        return level;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
