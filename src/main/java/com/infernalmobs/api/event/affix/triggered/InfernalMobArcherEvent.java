package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 弓手（archer）词条真正触发事件。
 * 额外暴露本次齐射的箭矢数量与速度，供外部修改。
 */
public class InfernalMobArcherEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private int arrowCount;
    private float speed;

    public InfernalMobArcherEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                  int arrowCount, float speed) {
        super("archer", SkillType.DUAL, mob, target, handle, level);
        this.arrowCount = arrowCount;
        this.speed = speed;
    }

    /** 本次齐射的箭矢数量。 */
    public int getArrowCount() {
        return arrowCount;
    }

    /** 修改本次齐射的箭矢数量。 */
    public void setArrowCount(int arrowCount) {
        this.arrowCount = Math.max(1, arrowCount);
    }

    /** 箭矢飞行速度。 */
    public float getSpeed() {
        return speed;
    }

    /** 修改箭矢飞行速度。 */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
