REM By: Zhean Ganituen (zry).
REM Batch file to run all commands needed to make the javadocs.

@echo off

REM Clear screen

cls

REM Make javadoc directory

if not exist "javadoc" mkdir "javadoc"

REM Make the javadocs
REM REMEMBER. To add all the java files in `src` here

javadoc -d javadoc src/HotelReservationSystem.java 
javadoc -d javadoc src/Hotel.java 
javadoc -d javadoc src/Reservation.java 
javadoc -d javadoc src/Room.java

REM END OF FILE