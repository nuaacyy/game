#!/usr/bin/env bash

cd ..
WORK_DIR=`pwd`
LOG_FILE_GATE="${WORK_DIR}/gate/log/gate.log"
LOG_FILE_HOME="${WORK_DIR}/home/log/home.log"
LOG_FILE_WORLD="${WORK_DIR}/world/log/world.log"
LOG_FILE_PUBLIC="${WORK_DIR}/public/log/public.log"

echo '停服-gate'
PIDS_GATE=`ps -ef | grep slg2d-gate | grep -v "grep" | awk '{print $2}'`
for pid in ${PIDS_GATE}
do
    echo "kill "${pid}
    kill ${pid}
done

echo '停服-home'
PIDS_HOME=`ps -ef | grep slg2d-home | grep -v "grep" | awk '{print $2}'`
for pid in ${PIDS_HOME}
do
    echo "kill "${pid}
    kill ${pid}
done

echo '停服-world'
PIDS_WORLD=`ps -ef | grep slg2d-world | grep -v "grep" | awk '{print $2}'`
for pid in ${PIDS_WORLD}
do
    echo "kill "${pid}
    kill ${pid}
done

echo '停服-public'
PIDS_PUBLIC=`ps -ef | grep slg2d-public | grep -v "grep" | awk '{print $2}'`
for pid in ${PIDS_PUBLIC}
do
    echo "kill "${pid}
    kill ${pid}
done

for i in `seq 24`; do
    RUNNING_NUM_GATE=`ps -ef | grep slg2d-gate | grep -v grep | wc -l`
    RUNNING_NUM_HOME=`ps -ef | grep slg2d-home | grep -v grep | wc -l`
    RUNNING_NUM_WORLD=`ps -ef | grep slg2d-world | grep -v grep | wc -l`
    RUNNING_NUM_PUBLIC=`ps -ef | grep slg2d-public | grep -v grep | wc -l`
    echo "正在运行的进程数：${RUNNING_NUM_GATE} ${RUNNING_NUM_HOME} ${RUNNING_NUM_WORLD} ${RUNNING_NUM_PUBLIC}"
    if [ ${RUNNING_NUM_GATE} -gt 0 ]; then
        #若进程还未关闭，则脚本sleep几秒
        echo "等待GATE进程结束"
        sleep 5
    elif [ ${RUNNING_NUM_HOME} -gt 0 ]; then
        #若进程还未关闭，则脚本sleep几秒
        echo "等待Home进程结束"
        sleep 5
    elif [ ${RUNNING_NUM_WORLD} -gt 0 ]; then
        #若进程还未关闭，则脚本sleep几秒
        echo "等待World进程结束"
        sleep 5
    elif [ ${RUNNING_NUM_PUBLIC} -gt 0 ]; then
        #若进程还未关闭，则脚本sleep几秒
        echo "等待Public进程结束"
        sleep 5
    else
        #若进程已经关闭，则跳出循环
        echo "进程结束~"
        break
    fi
done

# 显示当前位置
pwd

# 复制日志到特定目录下
cp -f ${LOG_FILE_GATE} ../
cp -f ${LOG_FILE_HOME} ../
cp -f ${LOG_FILE_WORLD} ../
cp -f ${LOG_FILE_PUBLIC} ../