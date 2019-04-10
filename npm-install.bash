#!/bin/bash -v
# npm install phantomjs  ; stuck at old React version
# npm install slimerjs   ; can't use current Firefox as of 2019-4-4
npm install karma karma-cljs-test  --save-dev
npm install karma-junit-reporter   --save-dev
npm install karma-chrome-launcher  karma-firefox-launcher  karma-safari-launcher  --save-dev
