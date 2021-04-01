#!/bin/zsh

for i in `ls`; /Applications/waifu2x-mac-app.app/Contents/MacOS/waifu2x -t a -s 2 -n 1 -i $i -o ../$i
