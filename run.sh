#!/usr/bin/env bash
set -e
if [ ! -d out ]; then echo "Please run ./build.sh first."; exit 1; fi
java -cp "out:*" kec.allocator.MainApp
