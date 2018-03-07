#!/bin/bash

find . -name '*~' | xargs rm -f
find . -name '*.class' | xargs rm -f
