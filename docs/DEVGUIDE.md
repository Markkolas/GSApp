## Prerequisites

### Java

In order to run Maven you will need to install Java's JDK but as long we are using Maven we didn't need to have the
specific version of Java that we are using to write owr project (Maven will use JDK11 compiler anyway). I recommend to install versions older 
that JDK8 (minimum JDK7). On my case I'm using JDK11.

I think that everybody who works with Java already knows how to set his JAVA_HOME system variable, but some of you use to set it like this:

```bash
    export JAVA_HOME="/usr/lib/jvm/java-11-openjdk/bin"
    
    export PATH=$JAVA_HOME/bin:$PATH

```

With the most of the programs this will works nice but Maven will not recognize this JAVA_HOME as a valid one. If you have set up
your JAVA_HOME like this the only think you need to do is to remove that /bin. 

```bash
    export JAVA_HOME="/usr/lib/jvm/java-11-openjdk"
    
    export PATH=$JAVA_HOME/bin:$PATH

```

### Maven

We have to install maven to package this project.

If you use **Arch Linux or Arch-based** distros:
```bash
	
	sudo pacman -S maven
	
```
If you use **ubuntu/devian-based** distros:

```bash

    sudo apt install maven

```
If you use **MacOS** in order to install maven you will have to install [Homebrew](https://brew.sh/)

```bash

    /usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

```
And now you can just install it 

```bash
    
    brew install maven
    
```
If you are using **Windows** download maven form the [maven website](http://maven.apache.org/download.cgi). And the follow the
guide and the advice of the [installation guide](http://maven.apache.org/install.html).



Now we can check if maven works properly

```bash

	mvn -version

```

You should get something like this:

```bash
	Apache Maven 3.6.3 (NON-CANONICAL_2019-11-27T20:26:29Z_root)
	Maven home: /opt/maven
	Java version: 11.0.6, vendor: Oracle Corporation, runtime: /usr/lib/jvm/java-11-openjdk
	Default locale: es_ES, platform encoding: UTF-8
	OS name: "linux", version: "5.4.15-arch1-1", arch: "amd64", family: "unix"

```

## Run

To run the project with java:

```bash

	cd glorydm
	
	mvn javafx:run

```
