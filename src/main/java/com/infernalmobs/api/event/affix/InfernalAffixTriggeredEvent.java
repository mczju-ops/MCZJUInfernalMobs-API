package com.infernalmobs.api.event.affix;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.triggered.InfernalMobThiefEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 词条真正触发事件基类：在某个词条技能通过条件与概率判定、最终效果参数已经计算，
 * 即将真正生效时触发。
 *
 * <p>每个技能都有一个专属子类（如 {@link InfernalMobThiefEvent}），便于外部按技能精确监听。
 * 公共字段：{@link #getAffixId()} / {@link #getSkillType()} / {@link #getMob()} /
 * {@link #getTarget()} / {@link #getHandle()} / {@link #getLevel()}，并支持 {@link #setCancelled(boolean)}。
 *
 * <p>到达本事件即表示本次词条已经成功触发。取消事件会阻止本次效果生效，但不会将其改回
 * “未触发”状态；插件本体仍应提交或保留本次触发对应的冷却。具体效果参数由各子类以类型化字段暴露。
 */
public abstract class InfernalAffixTriggeredEvent extends Event implements Cancellable {

    private final String affixId;
    private final SkillType skillType;
    private final LivingEntity mob;
    private final LivingEntity target;
    private final InfernalMobHandle handle;
    private final int level;
    private boolean cancelled = false;

    protected InfernalAffixTriggeredEvent(String affixId, SkillType skillType, LivingEntity mob,
                                          LivingEntity target, InfernalMobHandle handle, int level) {
        this.affixId = affixId;
        this.skillType = skillType;
        this.mob = mob;
        this.target = target;
        this.handle = handle;
        this.level = level;
    }

    /** 触发的词条 skillId（如 "thief"）。 */
    @NotNull
    public String getAffixId() {
        return affixId;
    }

    /** 词条技能类型。 */
    @NotNull
    public SkillType getSkillType() {
        return skillType;
    }

    /** 触发技能的炒鸡怪实体。 */
    @NotNull
    public LivingEntity getMob() {
        return mob;
    }

    /** 效果作用目标（玩家等）；死亡类等场景可能为 null。 */
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
