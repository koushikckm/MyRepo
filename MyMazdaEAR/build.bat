if %1Y == Y goto noparam
ant -buildfile build.xml
goto end
:noparam
ant
:end
End of execution
