#!/usr/bin/env bash

RELEASE_SERVER=172.18.3.201
RELEASE_PORT=10080

# 将目标主机添加到known_hosts中
mkdir -p ~/.ssh/
ssh-keyscan -p 2022 -H ${RELEASE_SERVER} | tee -a ~/.ssh/known_hosts

# 执行ansible脚本
ansible-playbook ${WORKSPACE}/ci/playbook/prepareLatestRelease.yml \
    -i ${WORKSPACE}/ci/playbook/hosts_inner.ini \
    --private-key=${keyfile} \
    -e "host=inner_releaseStore"