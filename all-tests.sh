#!/usr/bin/env bash

set -o errexit

pushd frontend
  npm test
popd
gradle clean test