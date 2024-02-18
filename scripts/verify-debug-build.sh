function echo_task {
  COLOR_CYAN='\033[0;36m'
  RESET_ATTR='\033[0m'
  echo -e "\n${COLOR_CYAN}$1${RESET_ATTR}"
}

function echo_step {
  echo -e "\n$1"
}

set -e
cd '../'

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
DOCS_PATH='docs'
BACKUP_PATH="build/tmp/$DOCS_PATH"
backup_created=false
if [ -d "$DOCS_PATH" ]; then
  mkdir -p "$BACKUP_PATH"
  mv "$DOCS_PATH" "$BACKUP_PATH"
  backup_created=true
fi
if ./gradlew :dokkaGfmMultiModule; then
  git add "$DOCS_PATH"
  if [ "$backup_created" = true ]; then
    rm -r "$BACKUP_PATH"
  fi
elif [ "$backup_created" = true ]; then
  mv "$BACKUP_PATH" "$DOCS_PATH"
fi
