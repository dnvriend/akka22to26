#!/bin/bash
docker exec -it postgres psql --dbname=docker --username=docker -c "alter table journal add column ordering BIGSERIAL;"
docker exec -it postgres psql --dbname=docker --username=docker -c "alter table journal add column deleted BOOLEAN DEFAULT FALSE;"
docker exec -it postgres psql --dbname=docker --username=docker -c "alter table journal alter column created drop not null;"
docker exec -it postgres psql --dbname=docker --username=docker -c "alter table journal alter column created set default 0;"
docker exec -it postgres psql --dbname=docker --username=docker -c "alter table journal add column created_ts TIMESTAMP DEFAULT CURRENT_TIMESTAMP;"
docker exec -it postgres psql --dbname=docker --username=docker -c "ALTER TABLE journal DROP CONSTRAINT journal_pkey;"
docker exec -it postgres psql --dbname=docker --username=docker -c "ALTER TABLE journal ADD PRIMARY KEY (ordering, persistence_id, sequence_number);"
docker exec -it postgres psql --dbname=docker --username=docker -c "CREATE INDEX journal_persistence_id_sequence_number_idx ON public.journal(persistence_id, sequence_number);"
#
# better not drop deleted_to be be v2.2.8 compatible
#docker exec -it postgres psql --dbname=docker --username=docker -c "DROP TABLE deleted_to;"
#
#