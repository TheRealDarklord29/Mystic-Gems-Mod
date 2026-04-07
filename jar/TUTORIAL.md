# How to build the mod JAR

This guide explains how to build the Mystic Gems mod into a JAR file.

## Requirements
- Java 21 installed
- Project opened from the correct folder:
  - `C:\dev\mystic-gems-mod-1.21.X`

## Build command

### Windows (PowerShell or CMD)
```powershell
cd C:\dev\mystic-gems-mod-1.21.X
.\gradlew.bat build
```

### Linux/macOS
```bash
cd /path/to/mystic-gems-mod-1.21.X
./gradlew build
```

## Output files
After a successful build, the JAR files are in:

`build/libs/`

You will usually see:
- `mystic-gems-mod-1.21.X-<version>.jar` (main mod JAR)
- `mystic-gems-mod-1.21.X-<version>-sources.jar` (sources JAR)

## Run in development

### Windows
```powershell
.\gradlew.bat runClient
```

### Linux/macOS
```bash
./gradlew runClient
```

## Common issues
- If Gradle says task not found, make sure you are in the project root (same folder as `build.gradle`).
- If IntelliJ run configs are broken, run from Gradle tasks directly (`runClient`).
