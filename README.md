# MCZJUInfernalMobs-API

炒鸡怪（InfernalMobs）**对外 API 独立项目**：只包含对外契约（接口 / 事件 / 枚举 / 门面），**不含插件本体实现**。

- 坐标（JitPack）：`com.github.mczju-ops:MCZJUInfernalMobs-API:1.4.0`
- 依赖方式：`provided`（编译期引用，运行时由 InfernalMobs 插件本体通过 `ServicesManager` 提供实现）
- 环境：Paper `api-version: '1.21.4'`、JDK 21+

包含内容：
- `com.infernalmobs.api.InfernalMobsApi` —— 服务接口
- `com.infernalmobs.api.InfernalMobHandle` —— 门面（等级 / 词条 / 显示名 / 禁用词条状态）
- `com.infernalmobs.api.InfernalAffix` —— 词条枚举
- `com.infernalmobs.api.event.*` —— 生成 / 装配 / 触发 / 掉落 / 击杀事件
- `com.infernalmobs.skill.SkillType` —— 技能类型枚举（事件契约）

新增能力：
- `isAffixSuppressed(LivingEntity entity, String skillId)`
- `setAffixSuppressed(LivingEntity entity, String skillId, boolean suppressed)`
- `InfernalMobHandle#isAffixSuppressed(String)` / `setAffixSuppressed(...)`

词条事件契约：
- `InfernalAffixAttemptEvent`：非 `STAT` 词条即将进行条件与概率判定；取消后不判定、不进入新冷却。
- `InfernalAffixTriggeredEvent`：运行期词条已通过判定且即将生效；取消效果仍视为成功触发，本体仍保留冷却或一次性机会等成本。
- `InfernalAffixEquippedEvent`：装配型词条的初始或常驻效果即将应用；取消只阻止本次装配效果，不删除词条。
- 技能最终效果参数由专用 Triggered 或 Equipped 事件以类型化字段暴露，不再通过通用字符串参数袋修改。
- `InfernalMobSulfurLaunchEvent`：sulfur 喷发时针对每名玩家的顶起事件，可单独取消或修改竖直速度。
- `InfernalMobFireworkDamageEvent`：firework 爆炸时针对每名受害者的伤害事件，可单独取消或修改基础伤害。
- `InfernalMobGhastlyDamageEvent`：ghastly 火球直接命中或爆炸时的逐受害者伤害事件。
- `InfernalMobNecromancerDamageEvent`：necromancer 凋灵之首直接命中或爆炸时的逐受害者伤害事件。
- `InfernalMobStormDamageEvent`：storm 真实闪电命中时的逐受害者伤害事件。

对接文档见主插件仓库 `docs/api.md`，并包含关于词条禁用状态的示例。

JitPack 发布方式：
- 将当前提交推送到 GitHub
- 在 GitHub 上创建并推送 tag（如 `1.0.0`）
- JitPack 会自动检测并构建：`com.github.mczju-ops:MCZJUInfernalMobs-API:1.0.0`
