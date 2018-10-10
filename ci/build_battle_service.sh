#!/usr/bin/env bash

# 检出战斗项目
cd ${WORKSPACE}
RELEASE_SVN=battle_svn
if [ ! -d ${RELEASE_SVN} ]; then
    svn co --username ${SVN_USER} --password ${SVN_PWD} http://172.18.3.204/svn/slg2d_frontend/trunk/SlgBattle ${RELEASE_SVN}
    if [ $? != 0 ]; then
        echo "svn检出战斗代码失败"
        exit 1
    fi
fi

# 还原更新
cd ${RELEASE_SVN}
svn revert -R .
svn up --username ${SVN_USER} --password ${SVN_PWD}
cat ${WORKSPACE}/${RELEASE_SVN}/SlgBattle/ServerBattle/appsettings.json

#删除svn上配置文件
rm -rf ${WORKSPACE}/${RELEASE_SVN}/SlgBattle/ServerBattle/Config

# 修改配置文件路径
sed -i 's/"CfgPath":[^,]*/"CfgPath": "..\/..\/config_game"/' ${WORKSPACE}/${RELEASE_SVN}/SlgBattle/ServerBattle/appsettings.json

# 删除发布包目录
if [ -d "$WORKSPACE/battle_publish" ];then
    rm -rf $WORKSPACE/battle_publish
fi

# 打包并压缩
cd SlgBattle/ServerBattle
dotnet publish -r centos.7-x64 -c:Release -o $WORKSPACE/battle_publish/centOS
dotnet publish -r ubuntu.18.04-x64 -c:Release -o $WORKSPACE/battle_publish/ubuntu
dotnet publish -r win10-x64 -c:Release -o $WORKSPACE/battle_publish/win10