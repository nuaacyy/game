#!/usr/bin/env bash

pwd

cd thirdlib/base-protocol

bash ./gradlew :protocol:install
bash ./gradlew :protocol:uploadArchives