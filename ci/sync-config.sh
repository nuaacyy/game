#!/usr/bin/env bash

which svn
echo $PATH

# 检出或更新策划配置
#if [ ! -d cehuaproto ]; then
#    echo "未找到策划配置目录，检出"
#    mkdir cehuaproto
#    svn co --username ${SVN_USER} --password ${SVN_PWD} http://192.168.189.12/svn/com.youzu.sexy.cehua/trunk/doc/Config/trunk cehuaproto
#fi
#cd cehuaproto
#svn revert .
#svn update

# 复制策划配置到后端项目中
#CURRENT_DIR=`pwd`
#cp -f cehuaproto/20lang/xlsx/* cehuaproto/3xlsx/
#cd cehuaproto
#bash generateConfig.sh
#GENERATE_RESULT=$?
#if [ $GENERATE_RESULT != 0 ]; then
#    echo "生成配置出错"
#    exit $GENERATE_RESULT
#fi
#
#cd $CURRENT_DIR

cp -f cehuaproto/2csv_server/* config_game/

#for file in $(ls cehuaproto/20lang/xlsx/)
#do
#    [ -f $file ] && rm -f cehuaproto/3xlsx/$file
#done


# 检测
bash gradlew :slg2d-common:checkConfigLocal
CHECK_RESULT=$?
if [ $CHECK_RESULT != 0 ]; then
    echo "配置检测出错"
    exit $CHECK_RESULT
fi

# 删除配置同步目录
if [ -d cehuaproto ]; then
    rm -rf cehuaproto
fi

# 提交并推送
git status
git config user.email "admin@szyouzu.com"
git config user.name "youzu.suzhou"
git add .
git status

git commit -m "Sync Config"
git push origin develop