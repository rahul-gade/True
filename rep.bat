@echo off
set TOPDIR=%~dp0..
set OPTS=
set MAIN_CLASS=net.sourceforge.pmd.PMD

java -classpath "lib\replib\*" %OPTS% %MAIN_CLASS% %*
