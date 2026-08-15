package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 母体（mama）词条真正触发事件。
 * 额外暴露本次生成的子怪数量，供外部读取 / 修改。
 */
public class InfernalMobMamaEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private int count;

    public InfernalMobMamaEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                int count) {
        super("mama", SkillType.PASSIVE, mob, target, handle, level);
        this.count = count;
    }

    /** 本次生成的子怪数量。 */
    public int getCount() {
        return count;
    }

    /** 修改本次生成的子怪数量。 */
    public void setCount(int count) {
        this.count = Math.max(0, count);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
