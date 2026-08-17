package com.infernalmobs.api.event.affix;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;

/**
 * 词条装配生效事件基类：在怪物装配词条、对应的初始或常驻效果即将应用时触发。
 *
 * <p>取消事件只阻止本次装配效果，不会从怪物的词条列表中删除该词条。
 * 具体效果参数由各子类以类型化字段暴露。
 */
public abstract class InfernalAffixEquippedEvent extends InfernalAffixEvent {

    protected InfernalAffixEquippedEvent(String affixId, SkillType skillType, LivingEntity mob,
                                         LivingEntity target, InfernalMobHandle handle, int level) {
        super(affixId, skillType, mob, target, handle, level);
    }
}
