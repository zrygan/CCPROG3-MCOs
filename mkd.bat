REM By: Zhean Ganituen (zry).
REM Batch file to run all commands needed to make the javadocs.

REM Clear screen

cls


REM Delete current javadoc directory

rmdir javadoc

REM Make javadoc directory

mkdir javadoc

REM Make the javadocs

javadoc -d javadoc -sourcepath src
