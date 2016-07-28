# akka persistence 2.2.8 to 2.6.3

[![License](http://img.shields.io/:license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)

A small project to try to migrate data from 2.2.8 to 2.6.3

# How to execute
1. First launch the database with the script `launch-postgres.sh`. It will create a schema that is v2.2.8 based.
2. Launch the v2.2.8 project, with the `launch-22.sh` script. It will persist 10 events and then stop. If you wish you can
   launch the project multiple times, it will recover and log the events (plain old strings) and then terminate automatically.
3. Alter the database schema with the `alter-schema.sh` script. This will add the `deleted` and `ordering` column to the database, nothing more.
4. Launch the v.2.6.3 project with the `launch-26-.sh` script. TIt will try to recover from the events; you should see 10 events while it recovers and then the program will stop.

Conclusion; v2.6.3 can read v2.2.8's data with a minimum of change to the journal.

