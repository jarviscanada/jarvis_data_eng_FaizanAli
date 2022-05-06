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

#contains info on cpu
lscpu_out=`lscpu`
#contains info on memory
mem_out=`cat /proc/meminfo`

hostname=$(hostname)
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}')
cpu_architecture=$(echo "$lscpu_out"  | egrep "^A.*e:" | awk '{print $2}')
cpu_model=$(echo "$lscpu_out"  | egrep "^M.*e:" | awk '{print $3 " " $4 " " $5 " " $6 " " $7 " " $8}')
cpu_mhz=$(echo "$lscpu_out"  | egrep "^CPU MHz" | awk '{print $3}')
l2_cache=$(echo "$lscpu_out"  | egrep "^L2.*e" | awk '{print $3}')
total_mem=$(echo "$mem_out"  | egrep "^M.*l:" | awk '{print $2}')
time_stamp=$(date +"%D %T")

#insert
insert_stmt="INSERT INTO host_info (hostname,cpu_number,cpu_architecture,cpu_model,cpu_mhz,l2_cache,total_mem,time_stamp)\
                VALUES ('$hostname',$cpu_number,'$cpu_architecture','$cpu_model',$cpu_mhz,$l2_cache,$total_mem,'$time_stamp');"

export PGPASSWORD=$psql_password

psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"

exit $?
