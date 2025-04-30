#!/bin/bash

DB_HOST="postgres-db"
DB_PORT="5432"
DB_USER="postgres"
DB_PASSWORD="password"
DB_NAME="postgres"

echo "Waiting for 'currency' table to exist..."

until PGPASSWORD=$DB_PASSWORD psql -h $DB_HOST -U $DB_USER -p $DB_PORT -d $DB_NAME \
  -tAc "SELECT to_regclass('public.currency')" | grep -q currency; do
  echo "Table not ready yet. Waiting..."
  sleep 2
done

echo "'currency' table is ready."