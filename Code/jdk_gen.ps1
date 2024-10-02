$OUTPUT_NAME = "jdk"

$modules = C:\Users\RetailAdmin\Desktop\Game\corretto-22.0.2\bin\jdeps.exe --print-module-deps '.\out\artifacts\Game_fight_jar\Game fight.jar'
C:\Users\RetailAdmin\Desktop\Game\corretto-22.0.2\bin\jlink.exe --module-path "%JAVA_HOME_22%\jmods" --add-modules $modules --output $OUTPUT_NAME