# Setting up and Running Selenium Server

This directory contains a Windows batch file that runs a standalone Selenium
Server. 

Assume that the Selenium Server downloaded in directory `SEServer`. To run
the batch file, copy the batch file `runseleniumserver.cmd` to the
`SEServer` directory, and simple type its name as the following example shows,

```
cd \SEServer
C:\SEServer>runseleniumserver.cmd
```

However, before you do so, make sure you have downloaded the Selenium Sever
software and vendor specific browser webdrivers.  

## Downloading Selenium Server

Download the standalone Selenium Server from 
[http://www.seleniumhq.org/download/](http://www.seleniumhq.org/download/).

The server is a Jar file, e.g., selenium-server-standalone-3.8.1.jar. 

Again,assuming that the Selenium Server downloaded in directory `SEServer`, 
you should save the file to this directory. 

## Downloading 3rd Party Browser Drivers

The script assumes that the drivers for Internet Explorer and Chrome are 
downloaded to subdirectory `vendor`, e.g.,, when you examine the content of 
the vendor directory, you should observe the result similar to the below,

```
vendor
  chromedriver.exe
  MicrosoftWebDriver.exe
```

The drivers can be downloaded from 
[http://www.seleniumhq.org/download/](http://www.seleniumhq.org/download/).

Just for convenience, you can download them from the URLs below,

* [Edge Driver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/#downloads)
* [Chrome Driver](https://sites.google.com/a/chromium.org/chromedriver/)

### Test Selenium Standalone Server

To test the Selenium Sever is running, you may use your browser to open the URL below,

```
[http://localhost:4444/wd/hub]([http://localhost:4444/wd/hub])
```
assuming your are running the Selenium Server at the localhost. For Chrome, you should be able to open a session. 
