# Folder Layout

This map helps developers quickly find where to make changes.

## Root

- `build.gradle`, `settings.gradle`, `gradle.properties`: Gradle + Fabric Loom build setup.
- `gradlew`, `gradlew.bat`: Gradle wrapper scripts.
- `README.md`: project overview and gameplay-facing setup notes.
- `jar/`: quick build helper docs/scripts for contributors.
- `docs/`: developer-focused architecture and contribution documentation.

## Source code

- `src/main/java/net/cp/gemsmod/`
  - `MysticGemsMod.java`: central registration entrypoint.
  - `MysticGemsClient.java`: client entrypoint.
  - `MysticGemsModDataGenerator.java`: data generation entrypoint.
  - `README.md`: package-level onboarding notes.

- `src/main/java/net/cp/gemsmod/item/`
  - item definitions and custom gem item behavior (`GemItem`).
  - `README.md`: package intent and extension notes.

- `src/main/java/net/cp/gemsmod/block/`
  - block definitions (Gem Infuser) and compatibility wrappers.
  - `README.md`: package intent and extension notes.

- `src/main/java/net/cp/gemsmod/registry/`
  - canonical registration entrypoints for blocks/items.
  - `README.md`: package intent and extension notes.

- `src/main/java/net/cp/gemsmod/screen/`
  - screen/container logic for custom block UIs.
  - `README.md`: package intent and extension notes.

- `src/main/java/net/cp/gemsmod/gem/`
  - gem system logic (types, effects, drops, tick updates, data manager).
  - `README.md`: package intent and extension notes.

- `src/main/java/net/cp/gemsmod/config/`
  - mod configuration loading and access.
  - `README.md`: package intent and extension notes.

- `src/main/java/net/cp/gemsmod/mixin/`
  - mixins for data persistence/hooks into Minecraft internals.
  - `README.md`: package intent and extension notes.

## Resources

- `src/main/resources/fabric.mod.json`
  - mod metadata and entrypoint declarations.

- `src/main/resources/mysticgemsmod.mixins.json`
  - registered mixin classes and mixin settings.

- `src/main/resources/assets/mysticgemsmod/`
  - `lang/`: translation keys
  - `models/item/`: item models
  - `models/block/`: block models
  - `blockstates/`: blockstate <-> model mapping

- `src/main/resources/data/mysticgemsmod/`
  - `recipes/`: crafting recipes
  - `loot_tables/`: drops/loot behavior

## Runtime-generated files

- `build/`: compilation outputs and remapped JARs.
- `run/`: dev runtime data and crash reports.
- `config/mysticgemsmod.json`: generated config with tuning values.

Do not commit generated runtime artifacts unless intentionally versioning examples.
