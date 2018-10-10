#!/usr/bin/env bash

## 当前目录
CURRENT_PATH=`pwd`
echo ${CURRENT_PATH}

OLD_DIR=old_tag

# 反正只在一个测试机器上使用，直接写死！
# 注意，重建DB的代码会从zk拉取db的名字，这里的DB名字要和db的相符。
DB=slg2d
DB_USER=root
DB_PWD=123456
DB_HOST=127.0.0.1
DB_PORT=13306

## 老版本tag名
TAG=$1

## 分支名
NEW_BRANCH=$2

PWD_PARAM=
if [ ${DB_PWD} != 'none' ]; then
    PWD_PARAM=-p${DB_PWD}
fi

# 先看下当前文件夹是否是干净的。
if [ -d ${OLD_DIR} ]; then
    rm -rf ${OLD_DIR}
fi

git status

# 生成数据库结构
create_db_schema()
{
    echo '重建数据库'
    local TARGET_DB=$1

    # 删除并创建数据库, 这个必须和后台中配置的数据库同名
    mysql -u${DB_USER} ${PWD_PARAM} -h ${DB_HOST} -P ${DB_PORT} -e "drop database if exists $TARGET_DB; create database $TARGET_DB"
    if [ $? != 0 ]; then
        echo "Error：重建新版本数据库出错"
        exit 1
    fi

    # 执行重建数据库
    echo '创建数据库'
    cp slg2d-rebuild-db/cfg/startconfig.json.tpl slg2d-rebuild-db/cfg/startconfig.json
    bash gradlew :slg2d-rebuild-db:run
    if [ $? != 0 ]; then
        echo "Error：生成新版本数据库出错"
        exit 1
    fi

    # 导出表结构
    echo '导出表结构'
    cd ${CURRENT_PATH}
    mysqldump --default-character-set=utf8mb4 -u${DB_USER} ${PWD_PARAM} -h ${DB_HOST} -P ${DB_PORT} -d ${TARGET_DB} > new.sql
    if [ $? != 0 ]; then
        echo "Error：dump新版本数据库出错"
        exit 1
    fi

    sed -i '/Dump completed on/c\-- Dump completed on' new.sql
}

# 获取老版本
checkout_old_tag()
{
    cd ${CURRENT_PATH}
    echo "检出老版本"

    if [ -d ${OLD_DIR} ]; then
        rm -rf ${OLD_DIR}
    fi
    git clone git@172.18.3.201:slg2d/slg2d.git ${OLD_DIR}
    if [ $? != 0 ]; then
        echo "Error：clone老版本代码出错"
        exit 1
    fi

    cd ${OLD_DIR}
    git checkout ${TAG}
    if [ $? != 0 ]; then
        echo "Error：checkout老版本代码出错"
        exit 1
    fi
}

# 读取目录，找到特定sql文件
TARGET_FILE=""
NEXT_OLD_VER=""
function find_spec_sql()
{
    for file in `ls $1`
    do
        if [ -f $1"/"${file} ];then
            local OLD_IFS="$IFS"
            IFS='-'
            local ARR=(${file})
            IFS="$OLD_IFS"
            echo "搜索的目标文件：${file}，包含的版本信息：${ARR[2]}，需要找寻的版本：$2"
            if [ "A"${ARR[2]} == "A$2" ];then
                TARGET_FILE="$file"
                NEXT_OLD_VER=${ARR[4]}
                break
            fi
        fi
    done
}

# 导入老版本数据库
import_old_db()
{
    echo "导入老版本数据库"

    local TARGET_DB=$1
    cd ${CURRENT_PATH}/${OLD_DIR}

    # 得到这个版本的代码的数据库版本
    local CURRENT_VER=$(python3 fetchCurrentDbVersion.py)

    # 再次删除并创建数据库
    echo '再次重建数据库'
    mysql -u${DB_USER} ${PWD_PARAM} -h ${DB_HOST} -P ${DB_PORT} -e "drop database if exists $TARGET_DB; create database $TARGET_DB"
    if [ $? != 0 ]; then
        echo "Error：重建老版本数据库出错"
        exit 1
    fi

    # 导入老的数据库结构
    echo "导入老版本数据库结构"
    mysql -u${DB_USER} ${PWD_PARAM} -h ${DB_HOST} -P ${DB_PORT} ${TARGET_DB} < sql/struct.sql
    if [ $? != 0 ]; then
        echo "Error：导入老版本数据库出错"
        exit 1
    fi

    # 应用各个版本的更新语句
    echo "导入已存在的版本更新sql"
    if [ -d ${CURRENT_PATH}/sql/updates ];then
        cd ${CURRENT_PATH}/sql/updates
        pwd
        local CHECK_VER=${CURRENT_VER}
        while :
        do
            # 找到符合版本的sql更新文件
            echo "寻找 DB-VERSION ${CHECK_VER} 对应的更新sql"
            find_spec_sql . ${CHECK_VER}
            if [ "A"${TARGET_FILE} == "A" ]; then
                break
            fi

            # 应用更新sql
            echo "应用 ${TARGET_FILE}"
            mysql -u${DB_USER} ${PWD_PARAM} -h ${DB_HOST} -P ${DB_PORT} ${TARGET_DB} < ${TARGET_FILE}
            if [ $? != 0 ]; then
                echo "Error：应用 ${TARGET_FILE} 出错"
                exit 1
            fi

            # 重置
            CHECK_VER=${NEXT_OLD_VER}
            TARGET_FILE=""
            NEXT_OLD_VER=""
        done
        TARGET_FILE=""
        NEXT_OLD_VER=""
    fi

    # 应用最新的更新语句
    echo "应用 update-at-dev.sql"
    cd ${CURRENT_PATH}/sql
    UPDATE_SQL_SIZE=`ls -l update-at-dev.sql | awk '{print $5}'`
    if [ ${UPDATE_SQL_SIZE} -eq 0 ]; then
        echo "Error：update-at-dev.sql为空，无需重建数据库"
        exit 1
    fi
    mysql -u${DB_USER} ${PWD_PARAM} -h ${DB_HOST} -P ${DB_PORT} ${TARGET_DB} < update-at-dev.sql
    if [ $? != 0 ]; then
        echo "Error：应用 update-at-dev.sql 出错"
        exit 1
    fi

    # 导出更新后的表结构
    echo "导出更新后的表结构，并修正不必要的差异"
    cd ${CURRENT_PATH}
    mysqldump --default-character-set=utf8mb4 -u${DB_USER} ${PWD_PARAM} -h ${DB_HOST} -P ${DB_PORT} -d ${TARGET_DB} > old.sql
    if [ $? != 0 ]; then
        echo "Error：dump老版本数据库出错"
        exit 1
    fi

    sed -i '/Dump completed on/c\-- Dump completed on' old.sql
}

# 对比表结构, 判断一致性
check_diff()
{
    echo "检测新老数据库差异"
    cd ${CURRENT_PATH}

    diff -B old.sql new.sql > game.diff
    cat game.diff
    if [ `cat game.diff | wc -c` != "0" ];then
        echo "Error：新老数据库存在差异"
        exit 1
    fi
}

# 提交新生成的表结构
commit_db_schema()
{
    # 保存新的数据库结构文件
    echo "提交数据库schema"
    cd ${CURRENT_PATH}
    cp -f new.sql ${CURRENT_PATH}/sql/struct.sql

    # 保存新的更新文件
    local CURRENT_VER=$(python3 fetchCurrentDbVersion.py)
    local NEW_VER=$(date +"%Y%m%d%H%M%S")
    echo "
    " >> ${CURRENT_PATH}/sql/update-at-dev.sql
    echo "UPDATE "'`version`'" SET db_version = '${NEW_VER}';" >> ${CURRENT_PATH}/sql/update-at-dev.sql
    cat ${CURRENT_PATH}/sql/update-at-dev.sql

    if [ ! -d ${CURRENT_PATH}/sql/updates ]; then
        mkdir -p ${CURRENT_PATH}/sql/updates
    fi

    TARGET_SQL="update-from-"${CURRENT_VER}"-to-"${NEW_VER}"-db.sql"
    echo "新更新文件名：${TARGET_SQL}"
    mv ${CURRENT_PATH}/sql/update-at-dev.sql ${CURRENT_PATH}/sql/updates/${TARGET_SQL}
    touch ${CURRENT_PATH}/sql/update-at-dev.sql

    # 修改版本文件
    python3 updateDbVersion.py -v ${NEW_VER}
    cat version.json

    # 删除不需要的文件和目录
    rm -f new.sql
    rm -f old.sql
    rm -f game.diff
    rm -rf ${OLD_DIR}

    cd ${CURRENT_PATH}
    git status
    git config user.email "admin@point18.com"
    git config user.name "point18"

    git add .
    git commit -m "提交新的数据库结构"
    if [ $? != 0 ]; then
        echo "Error：提交出错"
        exit 1
    fi

    git push --set-upstream origin ${NEW_BRANCH}
    if [ $? != 0 ]; then
        echo "Error：推送出错"
        exit 1
    fi
}

create_db_schema ${DB}
if [ $? != 0 ]; then
    exit 1
fi

checkout_old_tag
if [ $? != 0 ]; then
    exit 1
fi

import_old_db ${DB}
if [ $? != 0 ]; then
    exit 1
fi

check_diff
if [ $? != 0 ]; then
    exit 1
fi

commit_db_schema
if [ $? != 0 ]; then
    exit 1
fi