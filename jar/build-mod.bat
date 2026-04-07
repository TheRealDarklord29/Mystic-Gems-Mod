@echo off
setlocal

echo ============================================
echo Mystic Gems Mod - Build Script
echo ============================================

REM Go to project root (parent of this script's folder)
cd /d "%~dp0.."

echo.
echo Running Gradle build...
call .\gradlew.bat build

if errorlevel 1 (
    echo.
    echo Build failed. Check the output above for errors.
    exit /b 1
)

echo.
echo Build successful.
echo Output folder: %CD%\build\libs

if exist "%CD%\build\libs" (
    start "" "%CD%\build\libs"
)

exit /b 0
