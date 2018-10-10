#!/usr/bin/env bash

RELEASE_SERVER=172.18.3.201
RELEASE_PORT=10080

# 将目标主机添加到known_hosts中
mkdir -p ~/.ssh/
ssh-keyscan -p 57522 -H 139.196.151.210 | tee -a ~/.ssh/known_hosts

mkdir -p ${WORKSPACE}/ci/playbook/roles/deploy4test/files

curl -o ${WORKSPACE}/ci/playbook/roles/deploy4test/files/slg2d.tar.gz http://${RELEASE_SERVER}:${RELEASE_PORT}/slg2d-kt/latestV/slg2d.tar.gz

# 执行ansible脚本
ansible-playbook -vv ${WORKSPACE}/ci/playbook/deploy4test.yml \
    -i ${WORKSPACE}/ci/playbook/hosts_inner.ini \
    --private-key=${keyfile} \
    -e "host=out_4show cleandb=1"