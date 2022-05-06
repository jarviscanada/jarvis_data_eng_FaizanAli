# Introduction
Developed a monitoring agent program using Bash scripts for Linux OS. 
This program collects hardware specification and resource usage data for the system and then stores it into a relational database.
The database to store the information was created using PostgreSQL and deployed locally using a Docker image for Linux.

# Quick Start
Use markdown code block for your quick-start commands
- Start a psql instance using psql_docker.sh
- Create tables using ddl.sql
- Insert hardware specs data into the DB using host_info.sh
- Insert hardware usage data into the DB using host_usage.sh
- Crontab setup

# Implemenation
Discuss how you implement the project.
## Architecture
Draw a cluster diagram with three Linux hosts, a DB, and agents (use draw.io website). Image must be saved to the `assets` directory.

## Scripts
Shell script description and usage (use markdown code block for script usage)
- psql_docker.sh
- Host Info script: Collect hardware specifications and inserts them into the appropriate table
- Usage Infor script: Collect server usage data and insert it into the appropriate table. This script is executed every minute using Linux crontab to allow us to get data at regular intervals on usage.
- queries.sql: Used to get hardware specification and resource usage information stored in the database. This is used to understand how much resources are being used on average and make appropriate changes as required.

## Database Modeling
Describe the schema of each table using markdown table syntax (do not put any sql code)
- `host_info`
- `host_usage`

# Test
How did you test your bash scripts and SQL queries? What was the result?

# Deployment
How did you deploy your app? (e.g. Github, crontab, docker)

# Improvements
Write at least three things you want to improve
e.g.
- handle hardware update
- blah
- blah