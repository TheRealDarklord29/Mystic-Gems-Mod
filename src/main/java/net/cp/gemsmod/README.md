# `net.cp.gemsmod` package guide

This package contains the mod entrypoints and top-level coordination.

## Files
- `MysticGemsMod`: main/common Fabric entrypoint. Registers config, registries, and runtime systems.
- `MysticGemsClient`: client entrypoint for client-only hooks.
- `MysticGemsModDataGenerator`: data generation entrypoint.

## Rule of thumb
- Keep this package lightweight and orchestration-focused.
- Put feature logic into dedicated subpackages (`gem`, `item`, `block`, etc.).
