rem @echo off
rem C:\Programme\Java\jre7\bin\java -jar lib/jflex-1.6.1.jar src\edu\uap\tripla.flex
rem C:\Programme\Java\jre7\bin\java -jar lib/java-cup-11a.jar src\edu\uap\tripla.cup

rem copy parser.java src\edu\uap
rem copy sym.java src\edu\uap

rem c:\Programme\java\jdk1.7.0_06\bin\javac -classpath .;src\;lib\java-cup-11a.jar src\edu\uap\*.java


REM TESTS AUSFÜHREN

rem c:\Programme\java\jdk1.7.0_06\bin\java -classpath .;src\;lib\java-cup-11a.jar edu.uap.CompilerTest test\benchmark.txt

rem c:\Programme\java\jdk1.7.0_06\bin\java -classpath .;src\;lib\java-cup-11a.jar edu.uap.CompilerTest test\factorial.tripla 

rem c:\Programme\java\jdk1.7.0_06\bin\java -classpath .;src\;lib\java-cup-11a.jar edu.uap.CompilerTest test\defSemiExample.tripla 

rem c:\Programme\java\jdk1.7.0_06\bin\java -classpath .;src\;lib\java-cup-11a.jar edu.uap.CompilerTest test\wrapper.tripla

rem c:\Programme\java\jdk1.7.0_06\bin\java -classpath .;src\;lib\java-cup-11a.jar edu.uap.CompilerTest test\invalidProgram.tripla

c:\Programme\java\jdk1.7.0_06\bin\java -classpath .;src\;lib\java-cup-11a.jar edu.uap.CompilerTest test\complex.tripla