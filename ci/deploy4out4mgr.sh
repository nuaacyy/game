#!/usr/bin/env bash

RELEASE_SERVER=172.18.3.201
RELEASE_PORT=10080

# 将目标主机添加到known_hosts中
mkdir -p ~/.ssh/
ssh-keyscan -p 57522 -H 139.196.151.210 | tee -a ~/.ssh/known_hosts

# 获取发布文件到本地
LOCAL_FILE_DIR=${WORKSPACE}/ci/playbook/roles/deploymgr4test/files
mkdir -p ${LOCAL_FILE_DIR}
curl -o ${LOCAL_FILE_DIR}/slg2d-mgr.tar.gz http://${RELEASE_SERVER}:${RELEASE_PORT}/slg2d-kt/latestVersion/slg2d-mgr.tar.gz

# 执行ansible脚本
ansible-playbook -vv ${WORKSPACE}/ci/playbook/deploymgr4test.yml \
    -i ${WORKSPACE}/ci/playbook/hosts_inner.ini \
    --private-key=${keyfile} \
    -e "host=out_4show localfile=1"