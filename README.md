<div>
  <h1 align="center">edzy</h1>
  <h3 align="center">A debian and rpm package installer</h3>
</div>
<br/>

## Overview
Edzy is a user-friendly application designed for efficient package installation on Debian and Fedora-based distributions. The application boasts an improved user interface using JavaFX, providing a more modern and intuitive experience compared to the previous version (v1.0), which was built using Java Swing.

## Features
- **Modern UI**: Built with JavaFX for a smoother and more responsive user interface.
- **Package Installation**: Easily install `.deb` and `.rpm` packages on Debian and Fedora-based distributions.
- **Inspired by eddy**: The design and functionality are inspired by the popular Addy Popos tool.

## Screenshot
### Main Window:
![Screenshot](https://github.com/Sandesh2007/edzy/blob/main/Screenshots/Main.png)

### Debian:
![Screenshot](https://github.com/Sandesh2007/edzy/blob/main/Screenshots/Debian.png)

### Fedora:
![Screenshot](https://github.com/Sandesh2007/edzy/blob/main/Screenshots/Fedora.png)

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 17 or newer installed on your system.

### Installation on Debian/Fedora

**Make sure you have java-jdk installed on your system** 

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/edzy.git
   cd edzy

2. **To run**
    ```bash
    java -jar edzy.jar 

`You may get some warning. Don't worry it is normal`

## Notes

- Make sure you have the necessary permissions to install packages on your system.
- The application might require administrative privileges to perform installations.
- There are some crashing in Fedora when you try to install a `rpm` file with missing dependencies.

### Contributing
Contributions are welcome! Please fork the repository and submit pull requests.

### License
This project is licensed under the Apache License, Version 2.0 - see the LICENSE file for details.


### Acknowledgments
- Inspired by eddy

## TODO 

- List installed `deb` or `rpm` package 