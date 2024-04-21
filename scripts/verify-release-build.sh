set -e
cd "$(realpath "$(dirname "$0")")"
source logging-utils.sh
cd "../"

echo_task 'RUNNING END-TO-END TESTS'
echo_step '(1/4) Preparing device'
source scripts/prepare-device.sh
echo_step '(2/4) Deleting build directories'
./gradlew clean
echo_step '(3/4) Uninstalling test application'
./gradlew uninstallStaging
echo_step '(4/4) Running tests'
./gradlew connectedAndroidTest

echo_task 'INSTALLING RELEASE APPLICATION'
echo_step '(1/2) Uninstalling existing application'
./gradlew uninstallRelease
echo_step '(2/2) Installing release application'
./gradlew installRelease