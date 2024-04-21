set -e
cd "$(realpath "$(dirname "$0")")"
source logging-utils.sh
cd "../"

echo_task 'BUILDING THE PROJECT'
echo_step '(1/4) Deleting build directories'
./gradlew clean
echo_step '(2/4) Assembling app module'
./gradlew assembleDebug
echo_step '(3/4) Assembling dokka module'
./gradlew :app:dokka:assemble
echo_step '(4/4) Assembling lint module'
./gradlew :app:lint:assemble

echo_task 'INSPECTING CODE WITH LINT'
echo_step '(1/1) Running lint task'
./gradlew lintDebug

echo_task 'RUNNING UNIT TESTS'
echo_step '(1/3) Running tests in app module'
./gradlew :app:testDebug
echo_step '(2/3) Running tests in dokka module'
./gradlew :app:dokka:test
echo_step '(3/3) Running tests in lint module'
./gradlew :app:lint:test

echo_task 'GENERATING CODE DOCUMENTATION'
echo_step '(1/2) Publishing Dokka plugin to Maven Local'
./gradlew :app:dokka:publishToMavenLocal
echo_step '(2/2) Generating documentation'
source scripts/generate-docs.sh
