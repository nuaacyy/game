#!/usr/bin/env bash

TARGET_HOST=$1
TARGET_IP=$2
TARGET_SSH_PORT=$3
OTHER_PARAMS=$4

# 将目标主机添加到known_hosts中
mkdir -p ~/.ssh/
ssh-keyscan -p ${TARGET_SSH_PORT} -H ${TARGET_IP} | tee -a ~/.ssh/known_hosts

# 执行ansible脚本
ansible-playbook -vv ${WORKSPACE}/ci/playbook/deploy4test4mgr.yml \
    -i ${WORKSPACE}/ci/playbook/hosts_inner.ini \
    --private-key=${keyfile} \
    -e "host=${TARGET_HOST} ${OTHER_PARAMS}"