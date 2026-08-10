# MCZJUInfernalMobs-API

炒鸡怪（InfernalMobs）**对外 API 独立项目**：只包含对外契约（接口 / 事件 / 枚举 / 门面），**不含插件本体实现**。

- 坐标（JitPack）：`com.github.mczju-ops:MCZJUInfernalMobs-API:1.0.0`
- 依赖方式：`provided`（编译期引用，运行时由 InfernalMobs 插件本体通过 `ServicesManager` 提供实现）
- 环境：Paper `api-version: '1.21.4'`、JDK 21+

包含内容：
- `com.infernalmobs.api.InfernalMobsApi` —— 服务接口
- `com.infernalmobs.api.InfernalMobHandle` —— 门面（等级 / 词条 / 显示名）
- `com.infernalmobs.api.InfernalAffix` —— 词条枚举
- `com.infernalmobs.api.event.*` —— 生成 / 触发 / 掉落 / 击杀事件
- `com.infernalmobs.api.dye.*` —— Dye 对接 API
- `com.infernalmobs.skill.SkillType` —— 技能类型枚举（事件契约）

对接文档见主插件仓库 `docs/api.md`。
