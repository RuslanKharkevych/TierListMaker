set -e
cd "$(realpath "$(dirname "$0")")"
cd "../"

PATH="$PATH:$HOME/Library/Android/sdk/platform-tools"
adb shell settings put global window_animation_scale 1
adb shell settings put global transition_animation_scale 1
adb shell settings put global animator_duration_scale 1