--Group hosts by hardware info
--Group hosts by CPU number and sort by their memory size in descending order(within each cpu_number group)
select cpu_number,id,total_mem
from host_info
group by cpu_number,id
order by cpu_number desc;

--Average memory usage
--Average used memory in percentage over 5 mins interval for each host. (used memory = total memory - free memory).
