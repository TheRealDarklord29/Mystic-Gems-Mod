This is an open source mod inspired by the custom-made Gems Mod used on the Mystic SMP. It was originally made for Minecraft **1.21**.

You are free to do anything with it, because it is **CC0** content, but I would be happy if you gave credits to me if used, changed or re‑spread/resold.  
If you want to give me credits, my name is **CryonixPrime (Leon)**.

## Quick links
- Build tutorial: `jar/TUTORIAL.md`

## Features
- **Gem system** with multiple gem types:
  - Fire Gem (fire resistance focus)
  - Water Gem (water mobility focus)
  - Speed Gem (movement focus)
  - Strength Gem (combat focus)
- **Passive and active abilities**:
  - Passive effects apply while a gem is attuned and in inventory.
  - Active abilities trigger with right-click and use cooldowns.
- **Custom crafting station**:
  - `Gem Infuser` workbench block for thematic gem progression.
- **Persistence and progression**:
  - Player attunements are saved across relogs.
  - Gems can be obtained from mining chance, mob drops, and crafting.

## Gem controls (in-game)
- **Sneak + Right Click a gem**: Attune/Unattune that gem.
- **Right Click a gem**: Activate its active ability (if attuned and off cooldown).

## Crafting

### Gem Infuser (custom workbench)
Craft the Gem Infuser first:

```text
G I G
I C I
G I G
```

- `G` = Gold Ingot
- `I` = Iron Ingot
- `C` = Crafting Table

### Gem recipe layout
All four gems follow the same base structure:

```text
W   W
D N D
W   W
```

- `W` = Wither Star (four corners)
- `N` = Netherite Block (center)
- `D` = Dye (depends on gem)

Dye mapping:
- Fire Gem: `Red Dye`
- Water Gem: `Blue Dye`
- Speed Gem: `Lime Dye`
- Strength Gem: `Purple Dye`

## Getting started (developers)

### Requirements
- **Java 21+**
- **Gradle Wrapper** included in this repo (no local Gradle install needed)
- **Minecraft 1.21** with **Fabric Loader** and **Fabric API** for running the built mod

### Build the mod
From the project root:

```bash
./gradlew build        # on Linux/macOS
.\gradlew.bat build    # on Windows
```

The built JAR will be in:

```text
build/libs/
```

Use the `-dev` JAR for development and the normal JAR for distribution.

### Run the mod in a dev client
From the project root you can start a development client:

```bash
./gradlew runClient        # Linux/macOS
.\gradlew.bat runClient    # Windows
```

There is also a dedicated server run task:

```bash
./gradlew runServer        # Linux/macOS
.\gradlew.bat runServer    # Windows
```

These tasks are provided by Fabric Loom and use the configuration from `fabric.mod.json`.

## Config file
On first run, the mod writes:

`config/mysticgemsmod.json`

You can tune:
- max attuned gems
- mining and mob gem drop chances
- per-gem cooldowns
- per-gem active effect durations

## Mod ID and naming
- **Mod ID**: `mysticgemsmod`
- **Base package**: `net.cp.gemsmod`

When adding new content (items, blocks, etc.), use the same mod ID and package to keep everything consistent.

## License
This project is licensed under **CC0-1.0** (public domain equivalent). See the repository page or `fabric.mod.json` for details.
