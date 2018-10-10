SET CURRENTDIR=%~dp0

protoc --java_out=%CURRENTDIR%..\..\java mgrCommon.proto
protoc --java_out=%CURRENTDIR%..\..\java --grpc-java_out=%CURRENTDIR%..\..\java --plugin=protoc-gen-grpc-java=%CURRENTDIR%protoc-gen-grpc-java-1.9.0-windows-x86_64.exe gmRpc.proto

pause