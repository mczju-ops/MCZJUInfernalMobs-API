package com.infernalmobs.api.event.affix;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 词条事件公共基类。
 *
 * <p>该类只承载各生命周期事件共享的词条上下文与取消状态；具体触发时机由
 * {@link InfernalAffixAttemptEvent}、{@link InfernalAffixTriggeredEvent} 和
 * {@link InfernalAffixEquippedEvent} 定义。
 */
public abstract class InfernalAffixEvent extends Event implements Cancellable {

    private final String affixId;
    private final SkillType skillType;
    private final LivingEntity mob;
    private final LivingEntity target;
    private final InfernalMobHandle handle;
    private final int level;
    private boolean cancelled = false;

    protected InfernalAffixEvent(String affixId, SkillType skillType, LivingEntity mob,
                                 LivingEntity target, InfernalMobHandle handle, int level) {
        this.affixId = affixId;
        this.skillType = skillType;
        this.mob = mob;
        this.target = target;
        this.handle = handle;
        this.level = level;
    }

    /** 相关词条的 skillId（如 "thief"）。 */
    @NotNull
    public String getAffixId() {
        return affixId;
    }

    /** 词条技能类型。 */
    @NotNull
    public SkillType getSkillType() {
        return skillType;
    }

    /** 持有词条的炒鸡怪实体。 */
    @NotNull
    public LivingEntity getMob() {
        return mob;
    }

    /** 本次事件的交互或效果目标；没有独立目标时为 null。 */
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
}
