@echo off
setlocal enableextensions disabledelayedexpansion

    set "search=PMD"
    set "replace=Code-Review"

    set "textFile=CodeReviewReport.html"

    for /f "delims=" %%i in ('type "%textFile%" ^& break ^> "%textFile%" ') do (
        set "line=%%i"
        setlocal enabledelayedexpansion
        >>"%textFile%" echo(!line:%search%=%replace%!
        endlocal
    )