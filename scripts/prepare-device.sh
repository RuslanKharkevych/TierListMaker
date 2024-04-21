set -e
cd "$(realpath "$(dirname "$0")")"
cd "../"

PATH="$PATH:$HOME/Library/Android/sdk/emulator"
PATH="$PATH:$HOME/Library/Android/sdk/platform-tools"

while read -r line; do
  device_id=$(echo "$line" | awk '{print $1}')
  device_name=$(echo "$line" | grep -o 'model:[^ ]*' | sed 's/model://')
  physical_devices+=("$device_name $device_id")
  all_devices+=("$device_name")
done < <(adb devices -l | grep -v '^emulator' | grep -v '^offline'| sed '$d' | awk 'NR > 1')

while read -r emulator; do
  emulators+=("$emulator")
  all_devices+=("$emulator")
done < <(emulator -list-avds | tail -n +2)

PS3='Select device: '
select selected_device in "${all_devices[@]}"; do
  for device in "${physical_devices[@]}"; do
      device_name=$(echo "$device" | awk '{print $1}')
      device_id=$(echo "$device" | awk '{print $2}')
      if [ -n "$device_name" ] && [[ "$device_name" == "$selected_device" ]]; then
        echo "$selected_device is up and running"
        export ANDROID_SERIAL=$device_id
        break 2
      fi
    done

  while read -r emulator_id; do
    running_device=$(adb -s "$emulator_id" emu avd name | head -1 | sed 's/\r//')
    if [[ "$running_device" == "$selected_device" ]]; then
      echo "$selected_device is up and running"
      export ANDROID_SERIAL=$emulator_id
      break 2
    fi
  done < <(adb devices | grep -v 'offline' | sed '$d' | awk 'NR > 1 {print $1}')

  for device in "${emulators[@]}"; do
    if [[ "$device" == "$selected_device" ]]; then
      source scripts/start-emulator.sh "$selected_device"
      break 2
    fi
  done

  echo 'Invalid selection, please try again'
done
