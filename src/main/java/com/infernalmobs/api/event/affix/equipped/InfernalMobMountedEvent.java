package com.infernalmobs.api.event.affix.equipped;

import com.infernalmobs.api.InfernalMobHandle;
import com.infernalmobs.api.event.affix.InfernalAffixEquippedEvent;
import com.infernalmobs.skill.SkillType;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * 骑乘（mounted）词条装配生效事件。
 * 外部插件可修改本次依次尝试的坐骑候选及其生成位置。
 */
public class InfernalMobMountedEvent extends InfernalAffixEquippedEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    private List<MountCandidate> mountCandidates;
    private Location spawnLocation;

    public InfernalMobMountedEvent(LivingEntity mob, LivingEntity target, InfernalMobHandle handle, int level,
                                   @NotNull List<MountCandidate> mountCandidates,
                                   @NotNull Location spawnLocation) {
        super("mounted", SkillType.STAT, mob, target, handle, level);
        setMountCandidates(mountCandidates);
        setSpawnLocation(spawnLocation);
    }

    /**
     * 本次按顺序尝试的坐骑候选；首个成功承载骑手的候选成为最终坐骑。
     * 返回不可变列表，列表为空表示不生成坐骑，但本次装配事件仍已发生。
     */
    @NotNull
    public List<MountCandidate> getMountCandidates() {
        return mountCandidates;
    }

    /**
     * 替换本次按顺序尝试的坐骑候选。
     * 列表及其中元素均不可为 null；空列表表示不生成坐骑。
     */
    public void setMountCandidates(@NotNull List<MountCandidate> mountCandidates) {
        this.mountCandidates = List.copyOf(Objects.requireNonNull(mountCandidates, "mountCandidates"));
    }

    /** 本次坐骑的生成位置（返回副本）。 */
    @NotNull
    public Location getSpawnLocation() {
        return spawnLocation.clone();
    }

    /** 修改本次坐骑的生成位置（保存副本）。 */
    public void setSpawnLocation(@NotNull Location spawnLocation) {
        this.spawnLocation = Objects.requireNonNull(spawnLocation, "spawnLocation").clone();
    }

    /**
     * 一项坐骑候选。
     *
     * @param mountType 可生成的生物实体类型
     * @param infernal  成功挂载后是否将该坐骑炒鸡化
     */
    public record MountCandidate(@NotNull EntityType mountType, boolean infernal) {

        public MountCandidate {
            Objects.requireNonNull(mountType, "mountType");
            Class<?> entityClass = mountType.getEntityClass();
            if (!mountType.isSpawnable() || entityClass == null
                    || !LivingEntity.class.isAssignableFrom(entityClass)) {
                throw new IllegalArgumentException("mountType must be a spawnable LivingEntity type");
            }
        }
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
