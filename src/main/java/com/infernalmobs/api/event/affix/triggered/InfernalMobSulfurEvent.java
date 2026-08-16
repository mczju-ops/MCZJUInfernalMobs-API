package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 硫磺（sulfur）词条真正触发事件。
 * 外部插件可修改本次硫泉的中心、预警时间、范围、基础顶起速度、粒子柱高度与音效。
 */
public class InfernalMobSulfurEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private Location center;
    private int warnTicks;
    private double radius;
    private double upward;
    private double columnHeight;
    private Sound warnSound;
    private Sound eruptSound;
    private float soundVolume;

    public InfernalMobSulfurEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                  @NotNull Location center, int warnTicks, double radius, double upward,
                                  double columnHeight, @NotNull Sound warnSound,
                                  @NotNull Sound eruptSound, float soundVolume) {
        super("sulfur", SkillType.PASSIVE, mob, target, handle, level);
        setCenter(center);
        setWarnTicks(warnTicks);
        setRadius(radius);
        setUpward(upward);
        setColumnHeight(columnHeight);
        setWarnSound(warnSound);
        setEruptSound(eruptSound);
        setSoundVolume(soundVolume);
    }

    /** 本次硫泉已经过地面定位的中心（返回副本）。 */
    @NotNull
    public Location getCenter() {
        return center.clone();
    }

    /** 修改本次硫泉中心（保存副本）。 */
    public void setCenter(@NotNull Location center) {
        this.center = Objects.requireNonNull(center, "center").clone();
    }

    /** 喷发前持续显示预警的时间（tick）。 */
    public int getWarnTicks() {
        return warnTicks;
    }

    /** 修改预警时间（tick，负数按 0 处理）。 */
    public void setWarnTicks(int warnTicks) {
        this.warnTicks = Math.max(0, warnTicks);
    }

    /** 预警圈半径及喷发时的玩家搜索范围。 */
    public double getRadius() {
        return radius;
    }

    /** 修改作用范围（负数、NaN 和无穷值按 0 处理）。 */
    public void setRadius(double radius) {
        this.radius = Double.isFinite(radius) ? Math.max(0.0, radius) : 0.0;
    }

    /** 传给每个逐玩家喷发事件的基础竖直速度。 */
    public double getUpward() {
        return upward;
    }

    /** 修改基础竖直速度（负数、NaN 和无穷值按 0 处理）。 */
    public void setUpward(double upward) {
        this.upward = Double.isFinite(upward) ? Math.max(0.0, upward) : 0.0;
    }

    /** 喷发阶段粒子柱的最大高度。 */
    public double getColumnHeight() {
        return columnHeight;
    }

    /** 修改粒子柱高度（负数、NaN 和无穷值按 0 处理）。 */
    public void setColumnHeight(double columnHeight) {
        this.columnHeight = Double.isFinite(columnHeight) ? Math.max(0.0, columnHeight) : 0.0;
    }

    /** 预警开始时播放的音效。 */
    @NotNull
    public Sound getWarnSound() {
        return warnSound;
    }

    /** 修改预警音效。 */
    public void setWarnSound(@NotNull Sound warnSound) {
        this.warnSound = Objects.requireNonNull(warnSound, "warnSound");
    }

    /** 正式喷发时播放的音效。 */
    @NotNull
    public Sound getEruptSound() {
        return eruptSound;
    }

    /** 修改喷发音效。 */
    public void setEruptSound(@NotNull Sound eruptSound) {
        this.eruptSound = Objects.requireNonNull(eruptSound, "eruptSound");
    }

    /** 预警和喷发音效共用的音量。 */
    public float getSoundVolume() {
        return soundVolume;
    }

    /** 修改音量（负数、NaN 和无穷值按 0 处理）。 */
    public void setSoundVolume(float soundVolume) {
        this.soundVolume = Float.isFinite(soundVolume) ? Math.max(0.0f, soundVolume) : 0.0f;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
