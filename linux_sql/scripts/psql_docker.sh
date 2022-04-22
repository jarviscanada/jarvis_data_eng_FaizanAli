#!/bin/bash

#Setup arguments
cmd=$1
db_username=$2
db_password=$3

#variables
volume="pgdata"
container_name="jrvs-psql"
port=5432
image_name="postgres"
image_tag="9.6.23-alpine"

#if docker is not running run the docker start command
sudo systemctl status docker || sudo systemctl start docker &> /dev/null

 #get status of container jrvs-psql
docker container inspect $container_name &> /dev/null
container_status=$?

#run create,start,stop
case $cmd in
  create)

  if [ $container_status -eq 0 ]; then
    echo 'Container already exists'
    exit 1
  fi

  #if3 arguments not provided
  if [ $# -ne 3 ]; then
    echo 'Create requires username and password'
    exit 1
  fi

  #check if volume pgdata exists
  docker volume inspect $volume &> /dev/null
  volume_status=$?

  #if volume pgdata does not exist
  if [ $volume_status -ne 0 ]; then
    #create volume
    docker volume create $volume
  fi

  #create the container
  docker run --name $container_name -e POSTGRES_USER=$db_username -e POSTGRES_PASSWORD=$db_password -d -v $volume:/var/lib/postgresql/data -p $port:$port $image_name:$image_tag

  #exit with exit status of run command
  exit $?
  ;;
  start|stop)

    if [ $container_status -ne 0 ]; then
      echo 'Container does not exists'
      exit 1
    fi
    docker container $cmd $container_name
    exit $?

    ;;
esac