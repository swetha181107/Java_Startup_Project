#!/usr/bin/env bash
set -e
mkdir -p out
javac -encoding UTF-8 -d out ./*.java
echo "Build successful."
