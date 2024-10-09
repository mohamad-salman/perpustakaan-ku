#!/bin/bash

echo ""
echo "--> menghapus build sebelumnya..."
rm -rf target

mkdir -p target/classes
mkdir target/mods

echo "--> membuat modul perpusku-domain..."
javac -d target/classes/ms.perpusku.domain $(find perpusku-domain -name '*.java')

jar --create --file target/mods/ms.perpusku.domain@1.0.jar \
	-C target/classes/ms.perpusku.domain .

echo "--> membuat modul perpusku-data..."
javac --module-path target/mods \
	-d target/classes/ms.perpusku.data $(find perpusku-data -name '*.java')

jar --create --file target/mods/ms.perpusku.data@1.0.jar \
	-C target/classes/ms.perpusku.data .
 
echo "--> membuat modul perpusku-service..."
javac --module-path target/mods \
	-d target/classes/ms.perpusku.service $(find perpusku-service -name '*.java')

jar --create --file target/mods/ms.perpusku.service@1.0.jar \
	-C target/classes/ms.perpusku.service .


echo "--> membuat modul perpusku-frontend-cli..."
javac --module-path target/mods \
	-d target/classes/ms.perpusku.cli $(find perpusku-frontend-cli -name '*.java')

jar --create --file target/mods/ms.perpusku.cli@1.0.jar \
	--main-class ms.perpusku.cli.Main \
	-C target/classes/ms.perpusku.cli .

echo "--> membuat modul perpusku-frontend-gui..."
javac --module-path target/mods \
	-d target/classes/ms.perpusku.gui $(find perpusku-frontend-gui -name '*.java')

jar --create --file target/mods/ms.perpusku.gui@1.0.jar \
	--main-class ms.perpusku.gui.Main \
	-C target/classes/ms.perpusku.gui .
