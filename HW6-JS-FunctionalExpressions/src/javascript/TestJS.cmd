@echo off
if "%~1" == "" (
    echo Usage: %~n0 ^<variant^>
    exit /b 1
)
javac -d "_out" "--class-path=%~dp0..\javascript;%~dp0..\java" "%~dp0jstest\functional\FunctionalPieAvgMedTest.java" ^
    && java ^
        -ea ^
        "--module-path=%~dp0graal" ^
        "--class-path=_out" jstest.functional.FunctionalPieAvgMedTest "%~1"