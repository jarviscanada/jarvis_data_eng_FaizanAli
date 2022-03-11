/* 1. Group hosts by hardware info
    
Group hosts by CPU number and sort by their memory size in descending order(within each cpu_number group)

	sample output
	cpu_number,host_id,total_mem
	1,1,2048
	1,5,1568
	1,9,1024
	2,4,4088
	2,6,1024
	...
*/

SELECT cpu_number,id,total_mem
FROM host_info
ORDER BY cpu_number ASC;


/*2. Average memory usage
Average used memory in percentage over 5 mins interval for each host. (used memory = total memory - free memory).
	-- Sample query output
	-- host_id, host_name, timestamp,avg_used_mem_percentage
	1,node1.jrvs.ca,2019-01-01 00:00:00,97
	1,node1.jrvs.ca,2019-01-01 00:05:00,90
	1,node1.jrvs.ca,2019-01-01 00:10:00,65
	2,node1.jrvs.ca,2019-01-01 00:00:00,50
	2,node1.jrvs.ca,2019-01-01 00:05:00,40
	2,node1.jrvs.ca,2019-01-01 00:10:00,30 

*/

create function round5(ts timestamp) returns timestamp AS
$$
begin
	 RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;
    

SELECT host_id,host_info.hostname,round5(host_usage.time_stamp) AS timestamp,AVG(ROUND(((host_info.total_mem - host_usage.memory_free)*1.0/host_info.total_mem)*100,0)) AS avg_mem_used
FROM  host_usage
INNER JOIN host_info
ON host_id = host_info.id
GROUP BY host_id,host_info.hostname,timestamp
ORDER BY host_id,avg_mem_used;

/*3. Detect host failure
The cron job is supposed to insert a new entry to the host_usage table every minute when the server is healthy. We can assume that a server is failed when it inserts less than three data points within 5-min interval. Please write a query to detect host failures.
*/
-- same round5 function as question 2
create function round5(ts timestamp) returns timestamp AS
$$
begin
	 RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;


SELECT host_id,round5(host_usage.time_stamp) AS timestamp,COUNT(round5(host_usage.time_stamp)) AS total
FROM  host_usage
GROUP BY host_id,timestamp
HAVING COUNT(round5(host_usage.time_stamp)) <3

