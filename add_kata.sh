#!/usr/bin/env bash
#
# Adds a new kata.

# If a command fails then do not proceed and fail this script too
set -o errexit
set -o pipefail

read -rp "Please enter the package name: " NAME
echo
export NAME

if [[ -z "${NAME}" ]]; then
  echo 'Name was not entered'
  exit 1
fi

if [[ -d "src/main/java/com/vasileungureanu/katas/${NAME}" ]]; then
  echo 'Kata with this package name already exist'
  exit 1
fi

cp -r src/main/java/com/vasileungureanu/katas/template/ src/main/java/com/vasileungureanu/katas/"${NAME}"/
mkdir src/test/java/com/vasileungureanu/katas/"${NAME}"/

echo "${NAME} kata was added"