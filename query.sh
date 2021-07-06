#!/bin/sh
cd `dirname "$0"`
javac Parser.java
java Parser $1