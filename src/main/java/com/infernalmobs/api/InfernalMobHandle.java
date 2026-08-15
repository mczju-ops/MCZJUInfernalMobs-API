package com.infernalmobs.api;

import com.infernalmobs.api.event.mob.InfernalMobSpawnEvent;
import org.bukkit.entity.LivingEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 炒鸡怪 API 门面：对外提供稳定视图（等级 / 词条 / 显示名），
 * 不暴露插件内部模型（本类不依赖任何 {@code com.infernalmobs.model} 内部类）。
 *
 * <p>生成事件（{@link InfernalMobSpawnEvent}）中可编辑：
 * <ul>
 *   <li>{@link #setLevel(int)} — 修改等级（影响血量等数值与 [LvN] 前缀）</li>
 *   <li>{@link #setAffixes(List)} — 覆盖词条（影响技能装配与悬停工具提示）</li>
 *   <li>{@link #setDisplayName(String)} — 自定义显示名（MiniMessage），替代默认 [LvN] 前缀名</li>
 * </ul>
 * 编辑在生成事件里对装配生效（装配发生在事件之后）。
 */
public final class InfernalMobHandle {

    private final LivingEntity entity;
    /** 等级（可编辑）。 */
    private int level;
    /** 词条 skillId 列表（可编辑）。 */
    private List<String> affixSkillIds;
    /** 被禁用的词条 skillId 集合（大小写不敏感）。 */
    private final Set<String> suppressedAffixIds = new HashSet<>();
    /** 自定义显示名（MiniMessage），null = 用默认 [LvN] 前缀名。 */
    private String displayName;

    /**
     * @param entity         炒鸡怪实体
     * @param level          初始等级（下限 1）
     * @param affixSkillIds  初始词条 skillId 列表（可空）
     */
    public InfernalMobHandle(LivingEntity entity, int level, List<String> affixSkillIds) {
        this(entity, level, affixSkillIds, Set.of());
    }

    /**
     * @param entity               炒鸡怪实体
     * @param level                初始等级（下限 1）
     * @param affixSkillIds        初始词条 skillId 列表（可空）
     * @param suppressedAffixIds   初始被禁用词条集（可空）
     */
    public InfernalMobHandle(LivingEntity entity, int level, List<String> affixSkillIds,
                            Set<String> suppressedAffixIds) {
        this.entity = entity;
        this.level = Math.max(1, level);
        this.affixSkillIds = affixSkillIds == null ? List.of() : List.copyOf(affixSkillIds);
        if (suppressedAffixIds != null) {
            suppressedAffixIds.forEach(id -> {
                if (id != null) this.suppressedAffixIds.add(id.toLowerCase());
            });
        }
    }

    /** 炒鸡怪实体。 */
    public LivingEntity getEntity() {
        return entity;
    }

    /** 炒鸡怪等级。 */
    public int getLevel() {
        return level;
    }

    /** 设置炒鸡怪等级（下限 1）。 */
    public void setLevel(int level) {
        this.level = Math.max(1, level);
    }

    /** 词条 skillId 列表（只读）。 */
    public List<String> getAffixIds() {
        return List.copyOf(affixSkillIds);
    }

    /** 覆盖词条 skillId 列表；无效/未注册的 ID 会在装配时被忽略。 */
    public void setAffixes(List<String> skillIds) {
        this.affixSkillIds = skillIds == null ? List.of() : List.copyOf(skillIds);
    }

    /** 是否包含指定词条（大小写不敏感）。 */
    public boolean hasAffix(String skillId) {
        if (skillId == null) return false;
        return affixSkillIds.stream().anyMatch(id -> id.equalsIgnoreCase(skillId));
    }

    /** 指定词条是否被禁用（大小写不敏感）。 */
    public boolean isAffixSuppressed(String skillId) {
        return skillId != null && suppressedAffixIds.contains(skillId.toLowerCase());
    }

    /** 设置指定词条禁用状态（大小写不敏感）。 */
    public void setAffixSuppressed(String skillId, boolean suppressed) {
        if (skillId == null) return;
        String key = skillId.toLowerCase();
        if (suppressed) suppressedAffixIds.add(key);
        else suppressedAffixIds.remove(key);
    }

    /** 便捷重载：直接禁用指定词条。 */
    public void setAffixSuppressed(String skillId) {
        setAffixSuppressed(skillId, true);
    }

    /** 自定义显示名（MiniMessage 格式），null 表示使用默认 [LvN] 前缀名。 */
    public String getDisplayName() {
        return displayName;
    }

    /** 设置自定义显示名（MiniMessage 格式）；传 null 恢复默认 [LvN] 前缀名。 */
    public void setDisplayName(String miniMessage) {
        this.displayName = miniMessage;
    }
}
