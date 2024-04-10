import os
import subprocess
import sys
if os.geteuid() != 0:
        print("Error: This script must be run with sudo.")
        sys.exit(1)
subprocess.run(['sudo' ,'mv', 'edzy', '/usr/bin/'])
subprocess.run(['sudo' ,'mv', 'Edzy.jar', '/usr/bin/'])

print("installed sucessfully. to run edzy type edzy ")