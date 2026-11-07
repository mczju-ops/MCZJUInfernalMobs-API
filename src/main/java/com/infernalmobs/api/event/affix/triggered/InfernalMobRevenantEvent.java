package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 怨灵词条完成本次怨念融合判定时触发。
 * 事件取消会阻止本次怨念生成或吸收；事件只代表一次亡语触发，不代表最终恼鬼已经生成。
 */
public final class InfernalMobRevenantEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private final int addedLevel;
    private final int absorbedLevel;
    private final int absorbedCount;
    private final int totalLevel;
    private final int summonThreshold;
    private Location revenantLocation;

    public InfernalMobRevenantEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle,
                                   int level, @NotNull Location revenantLocation,
                                   int addedLevel, int absorbedLevel, int absorbedCount,
                                   int totalLevel, int summonThreshold) {
        super("revenant", SkillType.DEATH, mob, target, handle, level);
        this.revenantLocation = Objects.requireNonNull(revenantLocation, "revenantLocation").clone();
        this.addedLevel = addedLevel;
        this.absorbedLevel = absorbedLevel;
        this.absorbedCount = absorbedCount;
        this.totalLevel = totalLevel;
        this.summonThreshold = summonThreshold;
    }

    @NotNull
    public Location getRevenantLocation() {
        return revenantLocation.clone();
    }

    public void setRevenantLocation(@NotNull Location location) {
        this.revenantLocation = Objects.requireNonNull(location, "location").clone();
    }

    public int getAddedLevel() { return addedLevel; }
    public int getAbsorbedLevel() { return absorbedLevel; }
    public int getAbsorbedCount() { return absorbedCount; }
    public int getTotalLevel() { return totalLevel; }
    public int getSummonThreshold() { return summonThreshold; }
    public boolean isSummonReady() { return totalLevel >= summonThreshold; }

    @Override public @NotNull HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
