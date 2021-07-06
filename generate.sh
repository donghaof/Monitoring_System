#!/bin/sh
cd `dirname "$0"`
javac Generator.java
java Generator $1