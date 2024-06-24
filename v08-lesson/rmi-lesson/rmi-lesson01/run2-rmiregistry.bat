@echo off
rem 第二步，运行注册程序RMIRegistry，必须在包含刚写的类的目录下运行这个注册程序。

cd target/classes/

rem 1099是端口号。
rmiregistry 1099

cd ../../