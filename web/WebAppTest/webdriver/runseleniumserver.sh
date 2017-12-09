#!/bin/bash

export PATH=./:${PATH}
for f in selenium-server-standalone*.jar; do
    echo running $f
    java \
         -Dwebdriver.gecko.driver=./vendor/geckodriver \
         -Dwebdriver.chrome.driver=./vendor/chromedriver \
         -jar $f \
         -timeout 9999 -browserTimeout 999 \
         -debug
done

