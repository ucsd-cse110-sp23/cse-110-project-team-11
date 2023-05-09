#!/bin/sh -l
# run the build command

gradle build $1
# capture the exit code of the gradle command
# if it fails then exit the script
retval=$?
if [ ${retval} -ne 0 ]
then
    exit ${retval}
fi

# get the name of the jar file
fname=`ls $(pwd)/app/build/libs/`

# output the location variable into the output file location
# provided by the $GITHUB_OUTPUT variable
echo "jar-location=app/build/libs/${fname}" >> $GITHUB_OUTPUT
