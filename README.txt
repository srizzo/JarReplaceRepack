Replaces files in a Jar with matching files on a subdirectory


Example:

# Your jar with a config.properties file that'll be replace on each environment
/tmp/MyJar.jar                      

/tmp/test/config.properties         # Test environment version
/tmp/production/config.properties   # Production environment version

Usage: 

Just throw the JarReplaceRepack.jar at the working dir and run it

/tmp$ java -jar JarReplaceRepack.jar

Output:

> Created /tmp/test/MyJar.jar
> Replaced /tmp/test/MyJar.jar/config.properties with contents of /tmp/test/config.properties
> Created /tmp/production/MyJar.jar
> Replaced /tmp/production/MyJar.jar/config.properties with contents of /tmp/production/config.properties



