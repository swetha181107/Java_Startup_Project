@echo off
if not exist out (echo Please run build.bat first. & exit /b 1)
java -cp "out;*" kec.allocator.MainApp
