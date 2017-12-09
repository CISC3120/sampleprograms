@REM -------------------------------
@REM Running as a standalone server:
@REM -------------------------------
@REM
@REM Usage: java -jar selenium-server.jar [-interactive] [options]
@REM
@REM   -port <nnnn>: the port number the selenium server should use
@REM     (default 4444)
@REM   -timeout <nnnn>: an integer number of seconds we should allow a
@REM     client to be idle
@REM   -browserTimeout <nnnn>: an integer number of seconds a browser is
@REM     allowed to hang
@REM   -interactive: puts you into interactive mode.  See the tutorial for
@REM     more details
@REM   -singleWindow: puts you into a mode where the test web site
@REM     executes in a frame. This mode should only be selected if the
@REM     application under test does not use frames.
@REM   -profilesLocation: Specifies the directory that holds the profiles
@REM     that java clients can use to start up selenium.  Currently
@REM     supported for Firefox only.
@REM   -forcedBrowserMode <browser>: sets the browser mode to a single
@REM     argument (e.g. "*iexplore") for all sessions, no matter what is
@REM     passed to getNewBrowserSession
@REM   -forcedBrowserModeRestOfLine <browser>: sets the browser mode to
@REM     all the remaining tokens on the line (e.g. "*custom
@REM     /some/random/place/iexplore.exe") for all sessions, no matter what
@REM     is passed to getNewBrowserSession
@REM   -userExtensions <file>: indicates a JavaScript file that will be
@REM     loaded into selenium
@REM   -browserSessionReuse: stops re-initialization and spawning of the
@REM     browser between tests
@REM   -avoidProxy: By default, we proxy every browser request; set this
@REM     flag to make the browser use our proxy only for URLs containing
@REM     '/selenium-server'
@REM   -firefoxProfileTemplate <dir>: normally, we generate a fresh empty
@REM     Firefox profile every time we launch.  You can specify a directory
@REM     to make us copy your profile directory instead.
@REM   -debug: puts you into debug mode, with more trace information and
@REM     diagnostics on the console
@REM   -browserSideLog: enables logging on the browser side; logging
@REM     messages will be transmitted to the server.  This can affect
@REM     performance.
@REM   -ensureCleanSession: If the browser does not have user profiles,

@SET PATH=.;%PATH%
@FOR %%f IN (selenium-server-standalone*.jar) DO java ^
     -Dwebdriver.edge.driver=./vendor/MicrosoftWebDriver.exe^
     -Dwebdriver.chrome.driver=./vendor/chromedriver.exe^
     -jar %%f^
     -timeout 9999 -browserTimeout 999
