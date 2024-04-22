set -e
cd "$(realpath "$(dirname "$0")")"
cd "../"

DOCS_FOLDER_PATH='docs'
TEMP_FOLDER_PATH='build/tmp'
DOCS_BACKUP_FOLDER_PATH="$TEMP_FOLDER_PATH/$DOCS_FOLDER_PATH"

backup_created=false
if [ -d "$DOCS_FOLDER_PATH" ]; then
  mkdir -p "$DOCS_BACKUP_FOLDER_PATH"
  mv "$DOCS_FOLDER_PATH" "$DOCS_BACKUP_FOLDER_PATH"
  backup_created=true
fi

if ./gradlew :dokkaGfmMultiModule; then
  git add "$DOCS_FOLDER_PATH"
  if [ "$backup_created" = true ]; then
    rm -r "$DOCS_BACKUP_FOLDER_PATH"
  fi
elif [ "$backup_created" = true ]; then
  mv "$DOCS_BACKUP_FOLDER_PATH" "$DOCS_FOLDER_PATH"
  exit 1
fi

ROOT_DOC_FILE_PATH="$DOCS_FOLDER_PATH/index.md"
README_FILE_PATH='README.md'
TEMP_FILE_PATH="$TEMP_FOLDER_PATH/temp.md"

root_doc_file_text=''
while IFS= read -r line; do
  case $line in \|*)
    refined_row=$(echo "$line" | sed "s,\(\[[^]]*\]\)(\([^)]*\)),\1($DOCS_FOLDER_PATH/\2),g" | \
    sed -e 's/Name/Module/' -e 's/| |/| Description |/')
    root_doc_file_text+="\n$refined_row"
    ;;
  esac
done <$ROOT_DOC_FILE_PATH

awk -v pattern='## Documentation' -v text="$root_doc_file_text" \
'$0 ~ pattern {print; print text; exit} 1' \
$README_FILE_PATH > $TEMP_FILE_PATH && mv $TEMP_FILE_PATH $README_FILE_PATH