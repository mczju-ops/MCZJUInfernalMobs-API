package com.infernalmobs.api.event.mob;

import com.infernalmobs.api.InfernalMobHandle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 玩家击杀炒鸡怪时，等级池抽取次数确定后、保底累计与等级池抽取前同步触发。
 *
 * <p>仅在非保护动物、等级池已启用且当前轮换存在可抽取奖励时触发。
 * 调整后的次数同时用于等级池独立抽取和保底进度累计；不影响特殊实体奖励、
 * 词条奖励、原版掉落、经验或击杀统计。次数为抽取尝试数，物品创建失败不会重抽。
 *
 * <p>本事件不可取消；设置为 0 可跳过等级池与本次保底累计。
 * 独立调用 InfernalMobsApi 的等级池抽取方法不会触发本事件。
 */
public class InfernalMobLootRollEvent extends Event {
    public static final int MAX_ROLL_COUNT = 100;
    private static final HandlerList HANDLERS = new HandlerList();

    private final LivingEntity entity;
    private final InfernalMobHandle handle;
    private final Player killer;
    private final int level;
    private final int originalRollCount;
    private int rollCount;

    public InfernalMobLootRollEvent(LivingEntity entity, InfernalMobHandle handle,
                                   Player killer, int level, int originalRollCount) {
        this.entity = Objects.requireNonNull(entity, "炒鸡怪实体不能为空");
        this.handle = Objects.requireNonNull(handle, "炒鸡怪句柄不能为空");
        this.killer = Objects.requireNonNull(killer, "击杀玩家不能为空");
        this.level = level;
        this.originalRollCount = originalRollCount;
        setRollCount(originalRollCount);
    }

    public @NotNull LivingEntity getEntity() { return entity; }
    /** 只读的等级与词条快照。 */
    public @NotNull InfernalMobHandle getHandle() { return handle; }
    public @NotNull Player getKiller() { return killer; }
    public int getLevel() { return level; }
    /** 本次 drop-times 的原始随机结果，不受监听器修改或次数截断影响。 */
    public int getOriginalRollCount() { return originalRollCount; }
    public int getRollCount() { return rollCount; }
    /** 自动截断到 0 至 MAX_ROLL_COUNT；加成应基于 getRollCount() 以支持叠加。 */
    public void setRollCount(int rollCount) {
        this.rollCount = Math.clamp(rollCount, 0, MAX_ROLL_COUNT);
    }

    @Override
    public @NotNull HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
