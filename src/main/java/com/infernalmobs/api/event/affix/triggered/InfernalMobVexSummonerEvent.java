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
 * 唤魔（vexsummoner）词条真正触发事件。
 * 外部插件可修改本批恼鬼的数量与生成位置。
 */
public class InfernalMobVexSummonerEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private int summonCount;
    private Location spawnLocation;

    public InfernalMobVexSummonerEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                       int summonCount, @NotNull Location spawnLocation) {
        super("vexsummoner", SkillType.PASSIVE, mob, target, handle, level);
        setSummonCount(summonCount);
        setSpawnLocation(spawnLocation);
    }

    /** 本批生成的恼鬼数量；0 表示不生成，但本次词条仍已成功触发。 */
    public int getSummonCount() {
        return summonCount;
    }

    /** 修改本批生成的恼鬼数量（负数按 0 处理）。 */
    public void setSummonCount(int summonCount) {
        this.summonCount = Math.max(0, summonCount);
    }

    /** 本批恼鬼的生成位置（返回副本）。 */
    @NotNull
    public Location getSpawnLocation() {
        return spawnLocation.clone();
    }

    /** 修改本批恼鬼的生成位置（保存副本）。 */
    public void setSpawnLocation(@NotNull Location spawnLocation) {
        this.spawnLocation = Objects.requireNonNull(spawnLocation, "spawnLocation").clone();
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
