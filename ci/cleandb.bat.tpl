mysql -uroot -p123456 -e "drop database if exists slg2d; create database slg2d;"

mysql -uroot -p123456 slg2d < ../sql/struct.sql

pause