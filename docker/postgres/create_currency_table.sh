#!/bin/bash

DB_HOST="postgres-db"
DB_PORT="5432"
DB_USER="postgres"
DB_NAME="postgres"
DB_PASSWORD="password"

TABLE_NAME="currency"
SEQ_NAME="currency_seq"

echo "Checking for and creating table '$TABLE_NAME' and sequence '$SEQ_NAME' if not present..."

PGPASSWORD=$DB_PASSWORD psql -h $DB_HOST -p $DB_PORT -U $DB_USER -d $DB_NAME <<EOF
CREATE TABLE IF NOT EXISTS $TABLE_NAME (
    id serial primary key,
    code varchar(50),
    name varchar(50),
    usd_rate numeric(50, 2)
);
EOF