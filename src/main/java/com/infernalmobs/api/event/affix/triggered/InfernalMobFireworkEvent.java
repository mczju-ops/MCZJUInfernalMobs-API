package com.infernalmobs.api.event.affix.triggered;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixTriggeredEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 烟花（firework）词条真正触发事件。
 * 外部插件可修改本次烟花的生成位置和视觉效果。
 */
public class InfernalMobFireworkEvent extends InfernalAffixTriggeredEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private Location spawnLocation;
    private FireworkEffect fireworkEffect;

    public InfernalMobFireworkEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                    @NotNull Location spawnLocation,
                                    @NotNull FireworkEffect fireworkEffect) {
        super("firework", SkillType.DUAL, mob, target, handle, level);
        setSpawnLocation(spawnLocation);
        setFireworkEffect(fireworkEffect);
    }

    /** 本次烟花的生成位置。 */
    @NotNull
    public Location getSpawnLocation() {
        return spawnLocation;
    }

    /** 修改本次烟花的生成位置。 */
    public void setSpawnLocation(@NotNull Location spawnLocation) {
        this.spawnLocation = Objects.requireNonNull(spawnLocation, "spawnLocation");
    }

    /** 本次烟花使用的完整视觉效果。 */
    @NotNull
    public FireworkEffect getFireworkEffect() {
        return fireworkEffect;
    }

    /** 修改本次烟花使用的完整视觉效果。 */
    public void setFireworkEffect(@NotNull FireworkEffect fireworkEffect) {
        this.fireworkEffect = Objects.requireNonNull(fireworkEffect, "fireworkEffect");
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
