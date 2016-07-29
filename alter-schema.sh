#!/bin/bash
docker exec -it postgres psql --dbname=docker --username=docker -c "alter table journal add column ordering BIGSERIAL;"
docker exec -it postgres psql --dbname=docker --username=docker -c "alter table journal add column deleted BOOLEAN DEFAULT FALSE;"
docker exec -it postgres psql --dbname=docker --username=docker -c "alter table journal alter column created drop not null;"
docker exec -it postgres psql --dbname=docker --username=docker -c "alter table journal alter column created set default 0;"
docker exec -it postgres psql --dbname=docker --username=docker -c "alter table journal add column created_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP;"