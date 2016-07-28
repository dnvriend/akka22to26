#!/bin/bash
docker exec -it postgres psql --dbname=docker --username=docker -c "alter table journal add column ordering BIGSERIAL, add column deleted BOOLEAN DEFAULT FALSE;"
