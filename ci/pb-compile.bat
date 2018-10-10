SET CURRENTDIR=%~dp0
cd ..
cd ..
cd ..

cd design
cd proto

protoc_java --java_out="%CURRENTDIR%..\slg2d-common\src\main\java" client2server.proto

cd %CURRENTDIR%
pause