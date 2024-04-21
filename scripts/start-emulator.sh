set -e
cd "$(realpath "$(dirname "$0")")"
cd "../"

PATH="$PATH:$HOME/Library/Android/sdk/platform-tools"
EMULATOR_NAME=$1
START_TIMEOUT_SECONDS=10
ONLINE_TIMEOUT_SECONDS=30

emulator_started=false
initial_device_ids=$(adb devices | sed '$d' | awk 'NR > 1 {print $1}')

echo "Starting $EMULATOR_NAME"
emulator -avd "$EMULATOR_NAME" >/dev/null 2>&1 &

for ((i = 0; i < START_TIMEOUT_SECONDS; i++)); do
  sleep 1

  while read -r emulator_id; do
    emulator_id_found=false
    for initial_device_id in "${initial_device_ids[@]}"; do
      if [[ "$emulator_id" != "$initial_device_id" ]]; then
        emulator_id_found=true
        break
      fi
    done

    if [[ $emulator_id_found == true ]]; then
      echo "Waiting for $EMULATOR_NAME to become online"
      timeout $ONLINE_TIMEOUT_SECONDS adb -s "$emulator_id" wait-for-any-device
      export ANDROID_SERIAL=$emulator_id
      emulator_started=true
      echo "$EMULATOR_NAME is up and running"
      break 2
    fi
  done < <(adb devices | sed '$d' | awk 'NR > 1 {print $1}')
done

if [[ $emulator_started == false ]]; then
  echo 'Timed out waiting for emulator to start'
  exit 1
fi
