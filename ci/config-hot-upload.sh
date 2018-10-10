#!/usr/bin/env bash

which svn
echo $PATH

CURRENT_DIR=`pwd`

# 检出或更新策划配置
if [ ! -d cehuaproto ]; then
    echo "未找到策划配置目录，检出"
    mkdir -p cehuaproto
    svn co --username ${SVN_USER} --password ${SVN_PWD} http://192.168.189.12/svn/com.youzu.sexy.cehua/trunk/doc/Config/trunk cehuaproto
fi
cd cehuaproto
svn revert --username ${SVN_USER} --password ${SVN_PWD} .
svn update --username ${SVN_USER} --password ${SVN_PWD}

# 生成配置
echo ${SVN_USER}
bash generateConfig.sh
GENERATE_RESULT=$?
if [ ${GENERATE_RESULT} != 0 ]; then
    echo "生成配置出错"
    exit ${GENERATE_RESULT}
fi

cd ${CURRENT_DIR}

# 检测
bash gradlew :slg2d-common:checkHotUploadConfig
CHECK_RESULT=$?
if [ ${CHECK_RESULT} != 0 ]; then
    echo "配置检测出错"
    exit ${CHECK_RESULT}
fi

# 上传配置到zk
bash gradlew :code-lab:configHotUpload

# 删除配置同步目录
if [ -d cehuaproto ]; then
    rm -rf cehuaproto
fi