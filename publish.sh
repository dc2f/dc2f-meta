#!env bash


echo "You might want to update dc2fVersion in build.gradle."
echo
echo

git submodule foreach git pull

./gradlew publish


