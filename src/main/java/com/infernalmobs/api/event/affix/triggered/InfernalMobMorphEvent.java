package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 变形（morph）词条真正触发事件。
 * 额外暴露本次变形的目标实体类型，供外部读取 / 修改。
 */
public class InfernalMobMorphEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private EntityType targetType;

    public InfernalMobMorphEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                 EntityType targetType) {
        super("morph", SkillType.DUAL, mob, target, handle, level);
        this.targetType = targetType;
    }

    /** 本次变形的目标实体类型。 */
    @NotNull
    public EntityType getTargetType() {
        return targetType;
    }

    /** 修改本次变形的目标实体类型。 */
    public void setTargetType(@NotNull EntityType targetType) {
        this.targetType = targetType;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
