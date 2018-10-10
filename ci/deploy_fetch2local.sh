#!/usr/bin/env bash

RELEASE_SERVER=$1
RELEASE_PORT=$2

# 获取发布文件到本地
LOCAL_MGR_FILE_DIR=${WORKSPACE}/ci/playbook/roles/deploy4test4mgr/files
mkdir -p ${LOCAL_MGR_FILE_DIR}
curl -o ${LOCAL_MGR_FILE_DIR}/slg2d.tar.gz http://${RELEASE_SERVER}:${RELEASE_PORT}/slg2d-kt/latestV/slg2d.tar.gz

LOCAL_GAME_FILE_DIR=${WORKSPACE}/ci/playbook/roles/deploy4test/files
mkdir -p ${LOCAL_GAME_FILE_DIR}
curl -o ${LOCAL_GAME_FILE_DIR}/slg2d.tar.gz http://${RELEASE_SERVER}:${RELEASE_PORT}/slg2d-kt/latestV/slg2d.tar.gz