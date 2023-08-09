#!/bin/bash

echo "Start testing..."

#echo "Starting Selenium server..."
#java -jar selenium-server.jar&
#sleep 10s
#echo "Done."

# Setting defaults
if [[ -z "${PLATFORM}" ]]; then
  PLATFORM="linux"
fi;

if [[ -z "${BROWSER}" ]]; then
  BROWSER="cichrome"
fi;

if [[ -z "${HOST}" ]]; then
  HOST="localhost"
fi;

if [[ -z "${PROJECT}" ]]; then
  PROJECT=1
fi;

if [[ -z "${ENV}" ]]; then
  ENV="acceptance"
fi;

if [[ -z "${NUM_THREADS}" ]]; then
  NUM_THREADS=1
fi;

if [[ -z "${ADD_OPTS}" ]]; then
  ADD_OPTS=""
fi;

if [[ -z "${XML_FILE}" ]]; then
  XML_FILE="custom1.xml"
fi;

# Checking project name
case "${PROJECT}" in
     *) # default
        PROJECT_NAME="default" # testrail data
        PROJECT_ID=47
        FILTER_NAME="default"
        SUITE_ID=798
        ADD_OPTS="-DP_base.page=http://host.docker.internal:1620/ -DP_base.default.page=http://host.docker.internal:1620/"
        PROJECT=1
     ;;
esac

CURRENT_DATE=$(date +"%m-%d-%Y")
CURRENT_DATETIME=$(date +"%m-%d-%Y_%T")
RUN_NAME="Local-Smoke-Test-${FILTER_NAME}-${CURRENT_DATETIME}";
PLAN_NAME="Local-Smoke-Tests-${FILTER_NAME}-${CURRENT_DATE}";

# Summary information
echo "PROJECT:           ${PROJECT_NAME}"
echo "BROWSER:           $BROWSER"
echo "PLATFORM:          $PLATFORM"
echo "SELENIUM HOST:     $HOST"
echo "THREADS NUMBER:    $NUM_THREADS"

# XML suite generation
if [[ "${FILTER_NAME}" == "" ]]; then
    echo " ";
    echo "Generating a suite for ${PROJECT_NAME}..."
    cd $(find src -name connectors -type d | sed 1q); # В строке ниже вызывается коннектор с сис-мой хранения кейсов, но непросредственно в данном репозе коннктора нету, потому что коннектиться не к чему.
    java -jar connector.jar generate -f="/home/automation/TestXML" -o="/home/automation/"${XML_FILE}".xml" -s \
         -C="filters/smoke/${FILTER_NAME}.yml" -l="zabelin.portfolio.core.common.ExtentReportListener,zabelin.portfolio.core.common.Listener";
    cd /home/automation
    echo "Done."
fi;

# Performing a testrun
echo " "
echo "Testing..."
java -Dmenv=${ENV} -Dmplatform=${PLATFORM} -Dmbrowser=${BROWSER} -DthreadCount=${NUM_THREADS} \
     -DmpropsFile=src/main/resources/PropertyFiles/config.yml -Dmpropkey=${DECRYPTION_KEY} -DretryOnFailCount=1 \
     -Dmhost=${HOST} -DlocalRun=true ${ADD_OPTS} \
     -cp demo-1.0-fat-tests.jar zabelin.portfolio.base.util.TestsRunner ${XML_FILE}
echo "Done."
echo " "

# Sending results to testrail
echo "Generating a report and sending results..."
cp -r TestReport /reports/
cd $(find src -name connectors -type d | sed 1q);
java -jar connector.jar export -f=/home/automation/test-output/testng-results.xml -P=${PROJECT_ID} \
     -r="${RUN_NAME}" -p="${PLAN_NAME}" -S=${SUITE_ID};
cd /home/automation
echo "Done."
echo " "
echo "================================================"
echo "Your testrun has been finished!"
echo "Find your testrun here: https://default.testrail.com/index.php?/runs/overview/${PROJECT_ID}"
echo "Plan name: ${PLAN_NAME}"
echo "Run name: ${RUN_NAME}"
echo "================================================"

exit 0;
