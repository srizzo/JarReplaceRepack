Replaces files in a jar with matching files on a subdirectory. 

Example:

# A jar containing a file named config.properties
# anywhere in its folder hierarchy, including nested jars. 
# This file'll be replaced with the template in each profile.
/tmp/MyJar.jar                      

# Each sibling folder'll be treated as a different profile, and its templates used as replacements (matching by filename)
/tmp/test/config.properties         # Test environment version
/tmp/production/config.properties   # Production environment version

Usage: 

Just throw the JarReplaceRepack.jar at the same folder, and run it

/tmp$ java -jar JarReplaceRepack.jar

Output:

> Created /tmp/test/MyJar.jar
> Replaced /tmp/test/MyJar.jar/config.properties with contents of /tmp/test/config.properties
> Created /tmp/production/MyJar.jar
> Replaced /tmp/production/MyJar.jar/config.properties with contents of /tmp/production/config.properties



License: 

Public Domain