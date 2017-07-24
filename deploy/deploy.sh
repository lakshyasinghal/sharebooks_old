applicationPath="/Users/lakshya.singhal/Desktop/Development/Personal/Git/ShareBooks"
sourcePath="$applicationPath/webapp"

libPath="$sourcePath/lib"

buildPath="$applicationPath/build"
appName="sharebooks"
tomcatPath="/Users/lakshya.singhal/Desktop/tomcat/webapps"


webINFPath="$buildPath/$appName/WEB-INF"


javaFilesPath="$sourcePath/src"
classesInitialPath="$sourcePath/classes"
classesFinalPath="$webINFPath/classes"


dependancy[0]="mysql-connector-java-5.1.40-bin.jar"
dependancy[1]="javax.servlet.jar"


packages=( `cat "packages.txt" `)



function javaCompile {
	echo "compiling package $1"
	echo
	# improvements required
	#echo
	#echo
	#echo "classpath ----- $destPath:$libPath${dependancy[0]}:$libPath${dependancy[1]}"
	echo
	echo
	javac -d $classesInitialPath -classpath $classesInitialPath:$libPath/${dependancy[0]}:$libPath/${dependancy[1]} $1/*.java
}


function cleanDirectory {
	rm -r $1/*
}


function processPackages {
	echo "starting java files compilation"

	for t in "${packages[@]}"
	do
		echo
		echo
		echo
		javaCompile $javaFilesPath/$t
		echo
		echo
		echo
	done
		echo "Java files compilation complete"
}




# compile java files

function buildJavaFiles {
	echo "cleaning java resources"
	cleanDirectory $classesInitialPath

	processPackages
}




# create folder structure inside build folder
# create sharebooks folder
# create 

function createFolderStructure {
	echo "deleting the folders inside build directory"
	rm -R $buildPath

	echo "creating folder structure in build directory"

	mkdir -p $webINFPath/lib

	mkdir -p $webINFPath/classes
}



# move source files to the respective destinations in build folder
# Folders to be moved
# 1. Classes folder will be moved inside WEB-INF folder
# 2. etc/web.xml file will be moved inside the WEB-INF folder
# 3. lib folder will be moved inside WEB-INF folder
# 4. web/* will be moved inside sharebooks folder or application root folder

function moveSourceFiles {
	echo "moving classes"
	cp -R $sourcePath/classes/* $webINFPath/classes

	echo "moving web.xml"
	cp "$sourcePath/etc/web.xml" $webINFPath

	echo "moving lib folder"
	cp -R $sourcePath/lib/* $webINFPath/lib

	echo "moving contents of web"
	cp -R $sourcePath/web/* $buildPath/$appName
}








function deleteApp {
	echo
	echo "deleting old one"
	echo
	rm -R "$tomcatPath/$appName"
}




# move build to tomcat folder

function moveBuild {
	deleteApp
	
	echo
	echo "moving $appName app to tomcat webapps folder"
	echo
	
	cp -R "$buildPath/$appName" $tomcatPath
}







buildJavaFiles

createFolderStructure

moveSourceFiles

moveBuild
#done

