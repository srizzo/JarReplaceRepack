Replaces files in a Jar and Repacks it


Usage:

Put your jar in a directory, ex:

/tmp/MyJar.jar

Put your templates in a subdirectory, ex.

/tmp/mytemplates1/template1.txt


MyJar.jar will be copied to /tmp/mytemplates1/MyJar.jar, and each file named template1.txt in MyJar.jar (or nested jars) will have its content replaced with the contents of /tmp/mytemplates1/template1.txt

