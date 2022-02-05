#!/usr/bin/env bash

(./gradlew test && git commit -am "$1") || git reset --hard