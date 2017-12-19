#!/bin/bash
##############################################
#
# Script to test the web api 
#
# Copyright: Rikard Fröberg and Henrik Sandklef (c) 2017
# License:   GPLv3 (to short script to add the license text)
#
##############################################

#
# Web API settings
#
HOST=http://localhost:8080
SEARCH_PATH="/search/products/all?"
SEARCH_URL=${HOST}${SEARCH_PATH}

#
# Program settings
# - if you have different versions of a program you can use
# these settings (instead of changing your PATH variable)
# to make sure you use the tool you want
#
CURL=curl

#
# internal
#
ERR_COUNT=0
JSON_CHECK=false
VERBOSE=false

############################
#
# Function: usage()
# Arguments: none
#
# Description: prints help text and returns
#
############################
usage()
{
    echo "$(basename $0)"
    echo " - a script to test the wep api"
    echo " "
    echo "SYNTAX"
    echo " "
    echo " $(basename $0) [OPTIONS]"
    echo " "
    echo "OPTIONS"
    echo " -h, --help print this help text"
    echo " -j, --json check JSON validity"
    echo " -v, --verbose print extra information"
    echo " "
    echo "RETURN VALUES"
    echo " 0 on success"
    echo " 1 if program \"which\" is missing"
    echo " 2 if missing programs"
    echo " 3 if one or more tests failed"
}

############################
#
# Function: parse()
# Arguments: all the user arguments
#
# Description: parses and acts on user arguments
############################
parse()
{
    #
    # Parse user args
    #
    # Loop as long as the first arg is not empty
    # i e loop as long as the first arg contains something
    while [ "$1" != "" ]
    do
        # check the arg 
        case "$1" in
            "-v"|"--verbose")
                # verbose mode wanted
                # set VERBOSE to true
                VERBOSE=true
                ;;
            "-h"|"--help")
                # help text wanted
                # call the function usage
                usage
                # ... and exit with 0
                exit 0
                ;;
            "-j"|"--json")
                # Check for JSON validity
                JSON_CHECK=true
                ;;
        esac
        # the arg is used, throw it away
        shift
    done
}


############################
#
# Function: check_env()
# Arguments: none
#
# Description: checks the user's environment
#
############################
check_env()
{
    # define what commands to check 
    commands="$CURL "
    if [ "$JSON_CHECK" = "true" ]
    then
        commands="$commands jq "
    fi
    # currently no commands are missing, let's use "" to denote thatq
    missing=""

    which which &> /dev/null
    RES=$?
    if [ $RES -ne 0 ] ; then
        echo -e " *** ERROR ***\n *** You are missing the program \"which\". ***" 
        echo " *** You need to install it ***"
        exit 1 ;
    fi
    
    # loop though the commands (to check)
    for command in $commands
    do
        # check if we can find the command using which
        which $command &> /dev/null || missing="$missing $command"
        [[ "$missing" =~ $command ]] && echo "Uh oh, $command NOT FOUND!"
    done
    if [ "" != "$missing" ]
    then
        # hack to remove whitespace
        missing=$(echo $missing | xargs)
        echo -e " *** ERROR ***\n *** You are missing the program(s) \"${missing}\". ***" 
        echo " *** You need to install it/them ***"
        exit 2
    fi
}



############################
#
# Function: check_api()
# Arguments: 
#  1 - the search criteria
#  2 - expected number of hits from criteria (first arg)
#
# Description: calls the web api, counts the resulting number of products
# and ompares with the exepcted number of products 
#
############################
check_api()
{
    SEARCH_EXPR="$1"
    EXPECTED_HITS=$2

    echo -n "Checking \"${SEARCH_EXPR}\":"

    TMP_FILE=.test-web-api.json
    $CURL -s ${SEARCH_URL}${SEARCH_EXPR} > $TMP_FILE

    VALID_JSON=true
    if [ "$JSON_CHECK" = "true" ]
    then
 #       echo ldksdjf > $TMP_FILE
        jq '.' $TMP_FILE > /dev/null
        RES=$?
#        echo JSSON $RES
        if [ $RES -ne 0 ]
        then
            VALID_JSON=false
        fi
    fi

    
    
    ACTUAL_HITS=$(cat $TMP_FILE | grep -c "\"name\":")
    if [ $ACTUAL_HITS -ne ${EXPECTED_HITS} ] || [ "$VALID_JSON" = "false" ]
    then
        ERR_COUNT=$(( $ERR_COUNT + 1 ))
        echo " ERROR"
        echo "The following search failed"
    else
        echo " OK"
    fi
    if [ $ACTUAL_HITS -ne ${EXPECTED_HITS} ] || [ $VERBOSE = true ]
    then
        echo "* Command: $CURL -s ${SEARCH_URL}${SEARCH_EXPR}"
        echo "* Result:"
        echo " ** expected:   ${EXPECTED_HITS} products"
        echo " ** actual:     ${ACTUAL_HITS} products" 
        if [ $JSON_CHECK = true ]
        then
            echo " ** json valid: $VALID_JSON" 
        fi
        echo
    fi

    rm $TMP_FILE
    
} 

############################
#
# Function: check_errors_and_exit
# Arguments: none
#
# Description: Checks the error counter variable and exit accordingly
#
############################
check_errors_and_exit()
{
    if [ $ERR_COUNT -ne 0 ]
    then
        # return 2 if one or more errors
        exit 3
    fi
    # Great, no errors. Let's return 0
    exit 0
}

###################################################
#
#
# main - think of the below as your main method
#
#
###################################################



#
# Parse the user arguments
#
parse $*


#
# Make sure all tools are available
#
check_env

#
# Actual tests, add more tests if you want to
# ... you'll figure out the syntax ;)
#
check_api "" 20
check_api "min_alcohol=50" 4
check_api "min_price=100&max_price=200" 7
check_api "min_alcohol=40&max_price=900" 1
check_api "min_alcohol=10&max_price=200" 7

#
# Check error counter and exit
#
check_errors_and_exit
