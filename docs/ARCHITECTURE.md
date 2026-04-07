# Mystic Gems Mod Architecture

This document explains how the mod is structured and how features are wired together.

## High-level flow

1. Fabric loads `MysticGemsMod` from `fabric.mod.json`.
2. `MysticGemsMod#onInitialize()` registers:
   - config loading (`ModConfig`)
   - blocks (`BlockRegistry`)
   - items (`ItemRegistry`)
   - gem systems (drops + ticking handlers)
3. Mixins attach persistent gem data to players.
4. Runtime handlers apply passive effects, active abilities, drops, and cooldown behavior.

## Main modules

### `net.cp.gemsmod`
- `MysticGemsMod`: server/common entrypoint and registration hub.
- `MysticGemsClient`: client entrypoint (client-specific hooks).
- `MysticGemsModDataGenerator`: data generation entrypoint.

### `net.cp.gemsmod.item`
- `GemItem`: custom item behavior for gem activation and attunement.

### `net.cp.gemsmod.block`
- `GemInfuserBlock`: custom workbench block behavior and screen opening.

### `net.cp.gemsmod.registry`
- `BlockRegistry`: canonical block and block-item registration.
- `ItemRegistry`: canonical item and item group registration.

### Legacy compatibility wrappers
- `ModBlocks` and `ModItems` remain as thin wrappers to avoid breaking older imports.

### `net.cp.gemsmod.screen`
- `GemInfuserScreenHandler`: validates and powers the Gem Infuser crafting UI container.

### `net.cp.gemsmod.gem`
- `GemType`: canonical gem definitions and ability metadata.
- `GemAbilityHandler`: active/passive gem effect logic and visual/sound feedback.
- `GemDataManager`: attunement storage access and limits.
- `GemDataHolder`: interface for storing gem data on player entities.
- `GemDropHandler`: mining + mob-based gem acquisition.
- `GemTickHandler`: per-tick passive effect application.

### `net.cp.gemsmod.config`
- `ModConfig`: file-backed runtime config (`config/mysticgemsmod.json`) for balancing values.

### `net.cp.gemsmod.mixin`
- `PlayerEntityMixin`: persists gem player data to NBT.
- `ExampleMixin`: placeholder/example mixin.

## Data and resources

- `src/main/resources/fabric.mod.json`: mod metadata + entrypoints.
- `src/main/resources/mysticgemsmod.mixins.json`: mixin config.
- `src/main/resources/assets/mysticgemsmod/`:
  - `lang/`: translations
  - `models/`: item/block models
  - `blockstates/`: block state mappings
- `src/main/resources/data/mysticgemsmod/`:
  - `recipes/`: crafting recipes
  - `loot_tables/`: block drops

## Persistence model

- Player gem data is stored in `PlayerEntity` NBT under `MysticGemData`.
- Data survives relogs and world restarts.

## Extension points

- Add new gem type: update `GemType`, `ModItems`, lang/model/recipe resources, and optionally drop/config wiring.
- Add new balancing values: extend `ModConfig` and consume values in runtime handlers.
- Add custom visuals: update item models, block models, and particle/sound logic in `GemAbilityHandler`.
