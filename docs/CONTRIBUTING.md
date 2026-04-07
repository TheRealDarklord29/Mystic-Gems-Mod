# Contributing Guide

Thanks for contributing to Mystic Gems Mod.

## Development setup

1. Install Java 21.
2. Open this folder as a Gradle project.
3. Build once:
   - Windows: `.\gradlew.bat build`
   - Linux/macOS: `./gradlew build`
4. Run dev client:
   - Windows: `.\gradlew.bat runClient`
   - Linux/macOS: `./gradlew runClient`

## Coding conventions for this project

- Keep package names under `net.cp.gemsmod`.
- Prefer extending existing systems over adding parallel systems.
- Keep changes modular:
  - item logic in `item/`
  - block logic in `block/`
  - gem mechanics in `gem/`
  - persistence hooks in `mixin/`
- Use translation keys instead of hardcoded UI strings.
- Keep JSON resources consistent with naming style (`snake_case` IDs).

## Adding a new gem (checklist)

1. Add enum entry in `GemType`.
2. Register item in `ModItems`.
3. Add model in `assets/mysticgemsmod/models/item/`.
4. Add name in `assets/mysticgemsmod/lang/en_us.json`.
5. Add recipe in `data/mysticgemsmod/recipes/`.
6. Optionally wire drop sources in `GemDropHandler`.
7. Add/tune values in `ModConfig` if needed.

## Testing checklist before merging

- `build` passes.
- Gem Infuser opens and remains open.
- Gem recipes produce output.
- Attunement persists after relog.
- Passive and active effects trigger as expected.
- Cooldowns apply correctly.

## Documentation

If you change system behavior, update:
- `README.md` (user-facing behavior)
- `docs/ARCHITECTURE.md` (developer internals)
- `docs/FOLDER_LAYOUT.md` (if layout changes)
