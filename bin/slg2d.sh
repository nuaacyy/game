#!/usr/bin/env bash

if [ $# -lt 1 ]; then
    echo "usage: bash bin/slg2d.sh start|stop|deploy"
    exit 1
fi

command=$1

SLG2D_HOME=$(cd `dirname ${BASH_SOURCE[0]}`/../ && pwd)
echo "SLG2D_HOME=${SLG2D_HOME}"
echo ${BASH_SOURCE[0]}

if [ ! -d log ]; then
    mkdir -p log
fi

start_cluster_process() {
    local MAIN_CLASS=`main_class_of ${1}`
    echo ${MAIN_CLASS}

    local CP="${SLG2D_HOME}/shared_libs/*:${SLG2D_HOME}/project_libs/slg2d-${1}/*"

    screen -L -t ${1} -dmS ${1} java -cp ${CP} ${MAIN_CLASS}
}

start_battle_service() {
    screen -L -dmS battle dotnet battle_publish/ubuntu/ServerBattle.dll
}

main_class_of() {
    local ROLE=${1}
    echo "com.point18.slg2d.${ROLE}.MainKt"
}

wait_all_processes_exit_except_mgr() {
    while :
    do
        local RUNNING_PROCESSES=`jps -lv | grep MainKt | grep -v 'mgr' | awk '{print $2}'`
        if [ -n "${RUNNING_PROCESSES}" ]; then
            echo "等待进程推出，运行中的进程：${RUNNING_PROCESSES}"
            sleep 3
        else
            echo "所有进程都已经停止了"
            exit 0
        fi
    done
}

wait_all_processes_exit() {
    while :
    do
        local RUNNING_PROCESSES=`jps | grep MainKt | awk '{print $2}'`
        if [ -n "${RUNNING_PROCESSES}" ]; then
            echo "等待进程推出，运行中的进程：${RUNNING_PROCESSES}"
            sleep 3
        else
            echo "所有进程都已经停止了"
            exit 0
        fi
    done
}

start() {
    local CLUSTER_ROLE=${1:?Missing param role}

    if [ ${CLUSTER_ROLE} = "battle" ];then
        start_battle_service
    else
        echo "准备启动 ${CLUSTER_ROLE}"
        start_cluster_process ${CLUSTER_ROLE}
    fi
}

start_all() {
    start_cluster_process world
    start_cluster_process gate
    start_cluster_process home
    start_cluster_process public
    start_cluster_process mgr
    start_battle_service
}

stop() {
    local CLUSTER_ROLE=${1:?Missing param role}

    local MAIN_CLASS=`main_class_of ${1}`
    local PIDS=`jps -lv | grep ${MAIN_CLASS} | awk '{print $1}'`
    local PID_NUM=`echo ${PIDS} | wc -l`

    if [ ${PID_NUM} -gt 1 ]; then
        echo "进程不是唯一的，取消，找到进程：${PIDS}"
        exit 1
    elif [ ${PID_NUM} -gt 0 ]; then
        echo "kill ${PIDS}"
        kill ${PIDS}
    else
        echo "没有进程可以停止"
    fi
}

stop_all_except_mgr() {
    stop_battle_service

    local PIDS=`jps -lv | grep MainKt | grep -v 'mgr' | awk '{print $1}'`
    if [ -z "${PIDS}" ]; then
        echo "找不到可以停止的进程 $PATH"
        exit 1
    fi

    echo "kill ${PIDS}"
    kill ${PIDS}

    wait_all_processes_exit_except_mgr
}

stop_all() {
    stop_battle_service

    local PIDS=`jps -lv | grep MainKt | awk '{print $1}'`
    if [ -z "${PIDS}" ]; then
        echo "找不到可以停止的进程 $PATH"
        exit 1
    fi

    echo "kill ${PIDS}"
    kill ${PIDS}

    wait_all_processes_exit
}

stop_battle_service() {
    PIDS=`ps -ef | grep ServerBattle | grep -v "grep" | awk '{print $2}'`
    echo ${PIDS}
    for id in ${PIDS}
    do
        kill -9 ${id}
        echo "killed battle process: $id"
    done
}

case ${command} in
    start-all)
        start_all
        ;;
    stop_all_except_mgr)
        stop_all_except_mgr
        ;;
    stop-all)
        stop_all
        ;;
    start)
        start ${2}
        ;;
    stop)
        stop ${2}
        ;;
#    deploy)
#        generate_deploy_files
#        ;;
    *)
        echo "命令无法识别: $1"
        ;;
esac