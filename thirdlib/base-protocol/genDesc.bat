cd protocol/src/main/proto

protoc --descriptor_set_out=../../../envelope.desc --include_source_info client2server.proto

cd ../../../..

:: call .\gradlew.bat clean runGen -Pmain=GenProtoKt

call .\gradlew.bat generateProto

call .\gradlew.bat clean build

call .\gradlew.bat install

call .\gradlew.bat uploadArchives


