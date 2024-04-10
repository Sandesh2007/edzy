import subprocess
import time

# Constants
DEBIAN_UBUNTU = ["ubuntu", "debian"]
ARCH = "arch"

def check_os_distribution():
    result = subprocess.check_output(["cat", "/etc/os-release"])
    result_str = result.decode("utf-8").lower()
    
    if any(distro in result_str for distro in DEBIAN_UBUNTU):
        print("OS detected: Linux 🐧 (Debian/Ubuntu)")
        time.sleep(1)
        return True
    elif ARCH in result_str:
        print("This app is not compatible with Arch Linux.")
        return False
    else:
        print("Unsupported OS. Exiting.")
        return False

def check_java_installed():
    try:
        subprocess.run(['java', '--version'], check=True, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
        
        return True
    except subprocess.CalledProcessError:
        return False

def install_java():
    try:
        subprocess.run(['sudo', 'apt', 'install', 'default-jre', '-y'], check=True)
        print("Java installed successfully.")
        runmain()
        return True
        
    except subprocess.CalledProcessError as e:
        print("Error: Failed to install Java.")
        print(e)
        return False
def runmain():
    subprocess.run(['java', '-jar', '--enable-preview','/usr/bin/Edzy.jar'])


def main():
    if not check_os_distribution():
        return

    if check_java_installed():
        print("Java is already installed.")
        runmain()
    else:
        print("Java is not installed. Installing...")

if __name__ == "__main__":
    main()
