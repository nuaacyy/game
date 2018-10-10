mysql -uroot -p123456 -e "drop database if exists slg2d; create database slg2d;"
mysql -uroot -p123456 slg2d < sql/struct.sql
start cmd /k "title gate&&java -cp shared_libs/*;project_libs/slg2d-gate/* com.point18.slg2d.gate.MainKt"
start cmd /k "title world&&java -cp shared_libs/*;project_libs/slg2d-world/* com.point18.slg2d.world.MainKt"
start cmd /k "title home&&java -cp shared_libs/*;project_libs/slg2d-home/* com.point18.slg2d.home.MainKt"
start cmd /k "title public&&java -cp shared_libs/*;project_libs/slg2d-public/* com.point18.slg2d.public.MainKt"
start ./battle_publish/win10/ServerBattle.exe