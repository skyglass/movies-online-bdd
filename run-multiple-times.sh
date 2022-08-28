!#/bin/bash

set -o errexit

for i in {0..10}
do
	./gradlew clean test --debug > "out$i.txt"
done
