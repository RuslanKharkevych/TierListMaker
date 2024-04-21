function echo_task {
  COLOR_CYAN='\033[0;36m'
  RESET_ATTR='\033[0m'
  echo -e "\n${COLOR_CYAN}$1${RESET_ATTR}"
}

function echo_step {
  echo -e "\n$1"
}