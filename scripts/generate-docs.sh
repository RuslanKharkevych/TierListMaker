set -e
cd "$(realpath "$(dirname "$0")")"
cd "../"

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
