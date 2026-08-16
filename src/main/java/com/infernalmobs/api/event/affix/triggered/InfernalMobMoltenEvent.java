package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 熔岩（molten）词条真正触发事件。
 * 外部插件可修改本次要求的最低剩余燃烧时间。
 */
public class InfernalMobMoltenEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private int fireTicks;

    public InfernalMobMoltenEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                  int fireTicks) {
        super("molten", SkillType.PASSIVE, mob, target, handle, level);
        setFireTicks(fireTicks);
    }

    /** 本次要求攻击者至少剩余的燃烧时间（tick）；0 表示不改变当前燃烧状态。 */
    public int getFireTicks() {
        return fireTicks;
    }

    /** 修改最低剩余燃烧时间（tick，负数按 0 处理）。 */
    public void setFireTicks(int fireTicks) {
        this.fireTicks = Math.max(0, fireTicks);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
