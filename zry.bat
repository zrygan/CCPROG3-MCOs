REM By: Zhean Ganituen (zry).
REM Batch file to run all commands needed for the HotelReservationSystem.Driver file.

@echo off

REM  Clear screen

cls

echo ===== COMPILE AND RUN JAVA FILES =====

REM Compile `Driver.java` using `javac`

javac src/HotelReservationSystem/*.java

REM Go to `src` directory

cd src

REM Run the HotelReservationSystem.Driver class using `java`

java HotelReservationSystem.Driver

REM Go back to previous directory

cd..

REM END OF FILE
