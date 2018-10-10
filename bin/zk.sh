#!/usr/bin/env bash

SLG2D_HOME=$(cd `dirname ${BASH_SOURCE[0]}`/../ && pwd)
echo "SLG2D_HOME=${SLG2D_HOME}"
echo ${BASH_SOURCE[0]}

if [ $# -lt 1 ]; then
    echo "usage: bash bin/zk.sh restore|backup"
    exit 1
fi
echo ${MAIN_CLASS}

CP="${SLG2D_HOME}/shared_libs/*:${SLG2D_HOME}/project_libs/slg2d-mgr/*"

java -cp ${CP} com.point18.slg2d.mgr.MainKt $1
