REM By: Zhean Ganituen (zry).
REM Batch file to delete all class files in src directory.

@echo off

REM Clear screen

cls

REM Go to src directory

cd src

REM Delete all `.class` files

del /s /q *.class

REM Display

echo All `.class` files have been deleted.

REM END OF FILE