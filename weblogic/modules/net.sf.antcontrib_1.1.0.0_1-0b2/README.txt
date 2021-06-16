Ant-Contrib library

This library is for contributed Ant tasks that have
not been approved for inclusion into the ant core or
optional library.

Requirements
-------------------------
Runtime:
	Requires APACHE Ant Version 1.5 or above.  Note, that output
	handlers on the ForEach task will not properly report the
	task which is outputting the message unless you are using
	Ant version 1.5.2 or greater.

	In addition, the <for> task will not work on versions prior
	to Ant 1.6

Compilation:
	Requires Ant Version 1.6 or greater to compile and build the
	package.


Inclusion in your project
-------------------------
	The easiest way to use the tasks is to use

<taskdef resource="net/sf/antcontrib/antlib.xml">
  <classpath>
    <pathelement location="your/path/to/ant-contrib-${version}.jar" />
  </classpath>
</taskdef>

in your build file.  If the jar file is on your CLASSPATH or in
ANT_HOME/lib you can even simplify this to read

<taskdef resource="net/sf/antcontrib/antlib.xml" />

For projects which will run under 1.5 versions, you would
use the .properties file instead of the antlib.xml file:

<taskdef resource="net/sf/antcontrib/antcontrib.properties">
  <classpath>
    <pathelement location="your/path/to/ant-contrib.jar" />
  </classpath>
</taskdef>

