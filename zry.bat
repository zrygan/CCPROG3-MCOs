REM By: Zhean Ganituen (zry).
REM Batch file to run all commands needed for the Driver file.

@echo off

REM  Clear screen

cls

echo ===== COMPILE AND RUN JAVA FILES =====

REM Go to src directory

cd src

REM Compile `Driver.java` using `javac`

javac *.java

REM Run the Driver class using `java`

java Driver 

REM Go back to previous directory
cd..

REM END OF FILE
