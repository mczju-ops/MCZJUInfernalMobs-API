package com.infernalmobs.api.event.affix;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.triggered.InfernalMobThiefEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;

/**
 * 词条真正触发事件基类：在某个词条技能通过条件与概率判定、最终效果参数已经计算，
 * 即将真正生效时触发。
 *
 * <p>每个运行期发动的技能都有一个专属子类（如 {@link InfernalMobThiefEvent}），便于外部按技能精确监听。
 * 公共字段：{@link #getAffixId()} / {@link #getSkillType()} / {@link #getMob()} /
 * {@link #getTarget()} / {@link #getHandle()} / {@link #getLevel()}，并支持 {@link #setCancelled(boolean)}。
 *
 * <p>到达本事件即表示本次词条已经成功触发。取消事件会阻止本次效果生效，但不会将其改回
 * “未触发”状态；插件本体仍应提交或保留本次触发对应的冷却、一次性机会等成本。
 * 具体效果参数由各子类以类型化字段暴露。
 */
public abstract class InfernalAffixTriggeredEvent extends InfernalAffixEvent {

    protected InfernalAffixTriggeredEvent(String affixId, SkillType skillType, LivingEntity mob,
                                          LivingEntity target, InfernalMobHandle handle, int level) {
        super(affixId, skillType, mob, target, handle, level);
    }
}
