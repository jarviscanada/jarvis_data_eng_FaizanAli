#!/bin/bash

#check # of args
if [ $# -ne 5 ]; then
  echo "Please Enter all required arguments in order: host,port,db name, username, password"
  exit 1
fi

#Setup arguments
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

mem_out=`cat /proc/meminfo`
top_stat=`top -n 1 -b`
disk_out=`df -BM --total`
hostname=$(hostname -f)

time_stamp=$(date +"%D %T")
memory_free=$(echo "$mem_out"  | egrep "^M.*ee:" | awk '{print $2}')
cpu_idle=$(echo "$top_stat"  | egrep "^%Cpu.*t" | awk '{print $8}')
cpu_kernel=$(echo "$top_stat"  | egrep "^%Cpu.*t" | awk '{print $4}')
disk_io=$(echo "$top_stat"  | egrep "^%Cpu.*t" | awk '{print $10}')
disk_available=$(echo "$disk_out"  | egrep "^tot.*-" | awk '{print $4}' | sed 's/M//')


host_id="(SELECT id FROM host_info WHERE hostname='$hostname')";

#insert
insert_stmt="INSERT INTO host_usage (host_id,time_stamp,memory_free,cpu_idle,cpu_kernel,disk_io,disk_available)\
                VALUES ($host_id,'$time_stamp',$memory_free,$cpu_idle,$cpu_kernel,$disk_io,$disk_available);"

export PGPASSWORD=$psql_password

psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"

exit $?
