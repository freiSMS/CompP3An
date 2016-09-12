cd D:\GitHub\CompilerbauP3MusterloesungAndre\CompilerbauP3MusterloesungAndre
java -jar lib/jflex-1.6.1.jar src\edu\uap\tripla.flex
java -jar lib/java-cup-11a.jar src\edu\uap\tripla.cup

zusammen:
java -jar lib/jflex-1.6.1.jar src\edu\uap\tripla.flex; java -jar lib/java-cup-11a.jar src\edu\uap\tripla.cup;

copy parser.java src\edu\uap
copy sym.java src\edu\uap

rem "C:\Program Files\Java\jdk1.8.0_91\bin\javac" -classpath .;src\;lib\java-cup-11a.jar src\edu\uap\*.java


REM TESTS AUSFÜHREN

rem "C:\Program Files\Java\jdk1.8.0_91\bin\java" -classpath .;src\;lib\java-cup-11a.jar edu.uap.CompilerTest test\benchmark.txt

rem "C:\Program Files\Java\jdk1.8.0_91\bin\java" -classpath .;src\;lib\java-cup-11a.jar edu.uap.CompilerTest test\factorial.tripla 

rem "C:\Program Files\Java\jdk1.8.0_91\bin\java" -classpath .;src\;lib\java-cup-11a.jar edu.uap.CompilerTest test\defSemiExample.tripla 

rem "C:\Program Files\Java\jdk1.8.0_91\bin\java" -classpath .;src\;lib\java-cup-11a.jar edu.uap.CompilerTest test\wrapper.tripla

rem "C:\Program Files\Java\jdk1.8.0_91\bin\java" -classpath .;src\;lib\java-cup-11a.jar edu.uap.CompilerTest test\invalidProgram.tripla

"C:\Program Files\Java\jdk1.8.0_91\bin\java" -classpath .;src\;lib\java-cup-11a.jar edu.uap.CompilerTest test\complex.tripla