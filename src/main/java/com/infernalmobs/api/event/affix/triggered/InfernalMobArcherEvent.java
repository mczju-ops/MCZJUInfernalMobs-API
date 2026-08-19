package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * 弓手（archer）词条真正触发事件。
 * 额外暴露本次齐射的箭矢数量、速度与散布参数，供外部修改。
 */
public class InfernalMobArcherEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private int arrowCount;
    private float speed;
    private double directionSpread;
    private float projectileSpread;

    public InfernalMobArcherEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                  int arrowCount, float speed, double directionSpread, float projectileSpread) {
        super("archer", SkillType.DUAL, mob, target, handle, level);
        setArrowCount(arrowCount);
        setSpeed(speed);
        setDirectionSpread(directionSpread);
        setProjectileSpread(projectileSpread);
    }

    /** 本次齐射的箭矢数量；0 表示不生成箭矢，但本次词条仍已成功触发。 */
    public int getArrowCount() {
        return arrowCount;
    }

    /** 修改本次齐射的箭矢数量（负数按 0 处理）。 */
    public void setArrowCount(int arrowCount) {
        this.arrowCount = Math.max(0, arrowCount);
    }

    /** 箭矢飞行速度；0 表示生成无初速度的箭矢。 */
    public float getSpeed() {
        return speed;
    }

    /** 修改箭矢飞行速度（负数、NaN 和无穷值按 0 处理）。 */
    public void setSpeed(float speed) {
        this.speed = Float.isFinite(speed) ? Math.max(0.0f, speed) : 0.0f;
    }

    /** 瞄准方向的逐轴随机扰动范围；每轴实际偏移范围为该值的正负一半。 */
    public double getDirectionSpread() {
        return directionSpread;
    }

    /** 修改瞄准方向的逐轴随机扰动范围（负数、NaN 和无穷值按 0 处理）。 */
    public void setDirectionSpread(double directionSpread) {
        this.directionSpread = Double.isFinite(directionSpread) ? Math.max(0.0, directionSpread) : 0.0;
    }

    /** 传给 Paper 箭矢生成接口的原生散布参数。 */
    public float getProjectileSpread() {
        return projectileSpread;
    }

    /** 修改 Paper 原生散布参数（负数、NaN 和无穷值按 0 处理）。 */
    public void setProjectileSpread(float projectileSpread) {
        this.projectileSpread = Float.isFinite(projectileSpread) ? Math.max(0.0f, projectileSpread) : 0.0f;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
