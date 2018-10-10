#!/usr/bin/env bash

# 显示一些环境信息
echo $PATH
java -version
echo ${JAVA_HOME}
pwd
ls -al

RELEASE_SERVER=172.18.3.201

# 构建游戏服
bash gradlew clean buildTar
if [ $? != 0 ]; then
    echo "构建失败"
    exit 1
fi

# 复制发布文件到文件服务器
mkdir -p ~/.ssh/
ssh-keyscan -p 2022 -H ${RELEASE_SERVER} | tee -a ~/.ssh/known_hosts
scp -P 2022 -i ${keyfile} ${WORKSPACE}/build/distTar/slg2d.tar.gz \
    testvm@${RELEASE_SERVER}:/home/testvm/docker/nginx/slg2d-kt/latestV

# 复制发布文件到发布版本库
## 删除老的版本库，重新检出
cd ${WORKSPACE}
RELEASE_SVN=release_svn
if [ ! -d ${RELEASE_SVN} ]; then
    svn co --username ${SVN_USER} --password ${SVN_PWD} http://172.18.3.204/svn/slg2d_backend_releases/trunk ${RELEASE_SVN}
    if [ $? != 0 ]; then
        echo "svn检出失败"
        exit 1
    fi
fi

## 发布新文件到版本库
cd ${RELEASE_SVN}
svn revert .
svn up --username ${SVN_USER} --password ${SVN_PWD}

## 删除不需要的文件
rm -rf bin
rm -rf cfg
rm -rf config_game
rm -rf project_libs
rm -rf shared_libs
rm -rf sql
rm -f *.sh
rm -f *.bat
rm -f *.properties

# 复制发布文件到svn目录并提交
cp -rf ${WORKSPACE}/build/dist/* .
cp -f ${WORKSPACE}/ci/fast_chg_commit.sh .
pwd
svn status
bash fast_chg_commit.sh ${SVN_USER} ${SVN_PWD} "服务器发布提交"
if [ $? != 0 ]; then
    echo "svn提交失败"
    exit 1
fi

cd ${WORKSPACE}