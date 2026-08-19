package com.infernalmobs.api;

import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

/**
 * 一次等级掉落池抽取产生的完整奖励数据。
 *
 * <p>命令与广播模板保留尚未解析的占位符；广播模板已经包含插件的默认文案兜底。
 * 本对象只承载数据，不会主动执行命令或发送广播。
 */
public record InfernalLootReward(
        String itemId,
        String lootDisplayName,
        ItemStack itemStack,
        List<String> commands,
        boolean broadcast,
        String broadcastMessage
) {

    public InfernalLootReward {
        itemId = itemId != null ? itemId : "";
        lootDisplayName = lootDisplayName != null ? lootDisplayName : itemId;
        itemStack = Objects.requireNonNull(itemStack, "itemStack").clone();
        commands = commands != null ? List.copyOf(commands) : List.of();
        broadcastMessage = broadcastMessage != null ? broadcastMessage : "";
    }

    /** 返回独立副本，调用方可安全修改。 */
    @Override
    public ItemStack itemStack() {
        return itemStack.clone();
    }
}
