#!/bin/sh

SVN_USER=$1
SVN_PWD=$2
SVN_COMMIT_MSG=$3

statuslist=`svn status`
count=0;

for x in ${statuslist}
do
	start=$(($count%2))

	if [ "$start" = "0" ]
	then
		type=$x
	fi

	if [ "$start" = "1" ]
	then
		file=$x
		case "$type" in
		"!")
			svn delete $file
			;;
		"?")
			svn add $file
			;;
		esac
	fi

	count=$(($count+1))

done

if [ "$count" -gt 0 ]
then
	svn ci --username ${SVN_USER} --password ${SVN_PWD} -m "${SVN_COMMIT_MSG}"
fi


