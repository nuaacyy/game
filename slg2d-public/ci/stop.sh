#!/usr/bin/env bash

cd bin
WORK_DIR=`pwd`
LOG_FILE="${WORK_DIR}/../log/public.log"
WAIT_FOR_SECONDS=15

echo -ne `date +"%Y/%m/%d %H:%M:%S"`":Stoping ${WORK_DIR}/public => ${LOG_FILE} ...\n" >> "${LOG_FILE}"

# Whether server is started?
SLG_PUBLIC_RUNING=`ps -ef | grep slg2d-public.jar | grep -v grep | wc -l`
if [ ${SLG_PUBLIC_RUNING} -eq 0 ]
then
    echo "Server havn't started"
	echo -ne "\tServer havn't started.\n" >> "${LOG_FILE}"
	exit 0
fi

echo '停服'
PIDS=`ps -ef | grep slg2d-public.jar | grep -v "grep" | awk '{print $2}'`
for pid in ${PIDS}
do
    echo "kill "${pid}
    kill ${pid} >> "${LOG_FILE}"
done

for i in `seq 12`; do
    RUNNING_NUM=`ps -ef | grep slg2d-public.jar | grep -v grep | wc -l`
    echo "正在运行的进程数："${RUNNING_NUM}
    if [ ${RUNNING_NUM} -gt 0 ]; then
        #若进程还未关闭，则脚本sleep几秒
        echo "等待进程结束"
        sleep 5
    else
        #若进程已经关闭，则跳出循环
        echo "进程结束~"
        break
    fi
done