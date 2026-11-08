@echo off
setlocal
if not exist out mkdir out
javac -encoding UTF-8 -d out *.java
if errorlevel 1 (echo Build failed. Check errors above. & exit /b 1)
echo Build successful.
endlocal
