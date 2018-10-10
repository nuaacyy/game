#!/usr/bin/env bash

if [ ! -d log ]
then
    mkdir -p log
fi

cd bin
WORK_DIR=`pwd` # Current directory
LOG_FILE="${WORK_DIR}/../log/mgr.log" # Server log file

echo -ne `date +"%Y/%m/%d %H:%M:%S"`":Starting ${WORK_DIR}/mgr => ${LOG_FILE} ...\n" > "${LOG_FILE}"

nohup java -jar slg2d-mgr.jar > "${LOG_FILE}" 2>&1 & # Ignore warning
#java -jar slg2d-mgr.jar >> "${LOG_FILE}" 2>&1 &
#disown

for i in `seq 6`; do
    RUNNING_NUM=`ps -ef | grep slg2d-mgr.jar | grep -v grep | wc -l`
    if [ ${RUNNING_NUM} -gt 0 ]; then
        #若进程还未关闭，则脚本sleep几秒
        echo "进程启动中"
        sleep 5
    else
        #若进程已经关闭，则跳出循环
        echo "进程意外退出"
        cat ${LOG_FILE}
        exit 1
    fi
done

echo "进程启动完毕"