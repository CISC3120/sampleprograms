# Setting up and Running Selenium Server

This directory contains a Windows batch file that runs a standalone Selenium
Server. 

Assume that the Selenium Server downloaded in directory SEServer. To run
the batch file, simple type its name as the following example shows,

```
cd \SEServer
C:\SEServer>runseleniumserver.cmd
```

## Downloading Selenium Server

Download the standalone Selenium Server from 
[http://www.seleniumhq.org/download/](http://www.seleniumhq.org/download/).

The server is a Jar file, e.g., selenium-server-standalone-2.52.0.jar.

## Downloading 3rd Party Browser Drivers

The script assumes that the drivers for Internet Explorer and Chrome are 
downloaded to subdirectory `32\`, e.g.,

```
32\
  chromedriver.exe
  IEDriverServer.exe
```

The drivers can be downloaded from 
[http://www.seleniumhq.org/download/](http://www.seleniumhq.org/download/).

Just for convenience, you can download them from the URLs below,

* [IE Driver](http://selenium-release.storage.googleapis.com/2.52/IEDriverServer_Win32_2.52.0.zip)
* [Chrome Driver](https://chromedriver.storage.googleapis.com/index.html?path=2.21/)

