# Setting up and Running Selenium Server

This directory contains a Windows batch file that runs a standalone Selenium
Server. 

Assume that the Selenium Server downloaded in directory `SEServer`. To run
the batch file, copy the batch file `runseleniumserver.cmd` to the
`SEServer` directory, and simple type its name as the following example shows,

```
cd \SEServer
runseleniumserver.cmd
```

Similarly, on Mac OS X or Linux, you do the following, 
```
cd /SEServer
runseleniumserver.sh
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
downloaded to subdirectory `vendor` under `SEServer`, e.g.,, when you examine 
the content of 
the vendor directory, you should observe the result similar to the below,

```
vendor
  chromedriver.exe
  MicrosoftWebDriver.exe
```

On Mac OS X or Linux, you should see the similar,

```
vendor
  chromedriver
  geckodriver
```
except that we have downloaded and saved the `chromedriver` and the `geckodriver`. 


The drivers can be downloaded from 
[http://www.seleniumhq.org/download/](http://www.seleniumhq.org/download/).

Just for convenience, you can download them from the URLs below,

* [Chrome Driver](https://sites.google.com/a/chromium.org/chromedriver/)
* [GeckoDriver](https://github.com/mozilla/geckodriver/releases)
* [Edge Driver](https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/#downloads)

### Test Selenium Standalone Server

To test the Selenium Sever is running, you may use your browser to open the URL below,

[http://localhost:4444/wd/hub]([http://localhost:4444/wd/hub])

assuming your are running the Selenium Server at the localhost. For Chrome or Firefox, you should be able to 
open a session in your system. For Firefox, it is necessary to add the directory where Firefox's binary executable
is to the operating system's search path. 
