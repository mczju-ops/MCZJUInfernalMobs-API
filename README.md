# MCZJUInfernalMobs-API

炒鸡怪（InfernalMobs）**对外 API 独立项目**：只包含对外契约（接口 / 事件 / 枚举 / 门面），**不含插件本体实现**。

- 坐标（JitPack）：`com.github.mczju-ops:MCZJUInfernalMobs-API:1.1.0`
- 依赖方式：`provided`（编译期引用，运行时由 InfernalMobs 插件本体通过 `ServicesManager` 提供实现）
- 环境：Paper `api-version: '1.21.4'`、JDK 21+

包含内容：
- `com.infernalmobs.api.InfernalMobsApi` —— 服务接口
- `com.infernalmobs.api.InfernalMobHandle` —— 门面（等级 / 词条 / 显示名 / 禁用词条状态）
- `com.infernalmobs.api.InfernalAffix` —— 词条枚举
- `com.infernalmobs.api.event.*` —— 生成 / 触发 / 掉落 / 击杀事件
- `com.infernalmobs.api.dye.*` —— Dye 对接 API
- `com.infernalmobs.skill.SkillType` —— 技能类型枚举（事件契约）

新增能力：
- `isAffixSuppressed(LivingEntity entity, String skillId)`
- `setAffixSuppressed(LivingEntity entity, String skillId, boolean suppressed)`
- `InfernalMobHandle#isAffixSuppressed(String)` / `setAffixSuppressed(...)`

对接文档见主插件仓库 `docs/api.md`，并包含关于词条禁用状态的示例。

JitPack 发布方式：
- 将当前提交推送到 GitHub
- 在 GitHub 上创建并推送 tag（如 `1.1.0`）
- JitPack 会自动检测并构建：`com.github.mczju-ops:MCZJUInfernalMobs-API:1.1.0`
