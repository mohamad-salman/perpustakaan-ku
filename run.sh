#!/bin/bash

cd target

if [[ $1 = "gui" ]];  then
	java --module-path mods --module ms.perpusku.gui
else
	java --module-path mods --module ms.perpusku.cli
fi
