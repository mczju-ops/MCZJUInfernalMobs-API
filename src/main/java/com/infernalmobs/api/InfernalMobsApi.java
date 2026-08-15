package com.infernalmobs.api;

import com.infernalmobs.api.event.affix.InfernalAffixAttemptEvent;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

/**
 * InfernalMobs 对外 API。由 InfernalMobsPlugin 通过 {@link org.bukkit.plugin.ServicesManager} 注册，
 * 外部插件（如 MagicItems）软依赖本插件后获取：
 * <pre>{@code
 * RegisteredServiceProvider<InfernalMobsApi> rsp =
 *         Bukkit.getServicesManager().getRegistration(InfernalMobsApi.class);
 * InfernalMobsApi api = rsp != null ? rsp.getProvider() : null;
 * }</pre>
 *
 * <p>事件（{@link InfernalAffixAttemptEvent} 等）由本插件直接
 * 通过 {@link org.bukkit.plugin.PluginManager#callEvent} 广播，无需经此接口。
 */
public interface InfernalMobsApi {

    /** 实体是否已被炒鸡化。 */
    boolean isInfernal(LivingEntity entity);

    /** 获取炒鸡怪的门面句柄（实体未炒鸡化时为空）。 */
    Optional<InfernalMobHandle> getHandle(LivingEntity entity);

    /**
     * 直接查询炒鸡怪词条 skillId 列表；实体未炒鸡化时返回空列表
     * （等价于 {@code getHandle(entity).map(InfernalMobHandle::getAffixIds).orElse(List.of())}）。
     */
    List<String> getAffixIds(LivingEntity entity);

    /** 查询某个词条是否被禁用；实体未炒鸡化时返回 false。 */
    default boolean isAffixSuppressed(LivingEntity entity, String skillId) {
        if (entity == null || skillId == null) return false;
        return getHandle(entity).map(handle -> handle.isAffixSuppressed(skillId)).orElse(false);
    }

    /** 设置某个词条禁用状态；实体未炒鸡化时无效。 */
    default void setAffixSuppressed(LivingEntity entity, String skillId, boolean suppressed) {
        if (entity == null || skillId == null) return;
        getHandle(entity).ifPresent(handle -> handle.setAffixSuppressed(skillId, suppressed));
    }

    /** 便捷重载：直接禁用指定词条。 */
    default void setAffixSuppressed(LivingEntity entity, String skillId) {
        setAffixSuppressed(entity, skillId, true);
    }

    /**
     * 获取词条的显示名（MiniMessage，可包含颜色）。
     * 解析顺序：skill_name.yml 配置 -> skills.<id>.display -> 英文 id。
     * 例如："poisonous" -> "<dark_green>剧毒</dark_green>"。
     */
    default String getAffixDisplayName(String affixId) {
        return null;
    }

    /**
     * 兼容别名：获取词条/技能显示名。
     */
    default String getSkillDisplayName(String skillId) {
        return getAffixDisplayName(skillId);
    }

    /**
     * 在指定位置生成一只指定类型 / 等级 / 词条的炒鸡怪（同样会触发 {@code InfernalMobSpawnEvent}）。
     *
     * @param type          实体类型
     * @param location      生成位置
     * @param level         等级
     * @param affixSkillIds 词条 skillId 列表（应非空；无效 ID 会被忽略）
     * @return 成功生成并炒鸡化的实体；类型无效、位置无效、词条全无效或生成事件被取消时返回 null
     */
    LivingEntity spawnInfernalMob(EntityType type, Location location, int level, List<String> affixSkillIds);

    /**
     * 在指定位置生成一只指定类型 / 等级 / 词条的炒鸡怪，并施加初始速度（如钓海怪时上钩弹射）。
     *
     * @param type          实体类型
     * @param location      生成位置
     * @param level         等级
     * @param affixSkillIds 词条 skillId 列表（应非空；无效 ID 会被忽略）
     * @param velocity      初始速度；null 表示不施加
     * @return 成功生成并炒鸡化的实体；类型无效、位置无效、词条全无效或生成事件被取消时返回 null
     */
    LivingEntity spawnInfernalMob(EntityType type, Location location, int level, List<String> affixSkillIds,
                                  @Nullable Vector velocity);

    /** API 版本，供依赖方做兼容判断。 */
    default int apiVersion() {
        return 1;
    }
}
