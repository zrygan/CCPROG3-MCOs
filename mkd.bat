REM By: Zhean Ganituen (zry).
REM Batch file to run all commands needed to make the javadocs.

@echo off

REM Clear screen

cls

REM Make javadoc directory

if not exist "javadoc" mkdir "javadoc"

REM Make the javadocs
REM REMEMBER. To add all the java files in `src` here

javadoc -d javadoc -sourcepath src Driver.java Hotel.java Reservation.java Room.java

REM END OF FILE