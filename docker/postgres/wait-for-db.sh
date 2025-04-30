#!/bin/bash

# Database credentials
DB_HOST="postgres-db"  # PostgreSQL container is on the same network, can be "localhost"
DB_PORT="5432"       # Default PostgreSQL port
DB_USER="postgres"
DB_PASSWORD="password"
DB_NAME="postgres"

# Wait for PostgreSQL to become available
echo "Waiting for PostgreSQL to be ready..."

until PGPASSWORD=$DB_PASSWORD psql -h $DB_HOST -U $DB_USER -p $DB_PORT -d $DB_NAME -c '\q' 2>/dev/null; do
  echo "PostgreSQL is unavailable - sleeping"
  sleep 2
done

echo "PostgreSQL is up - running the create_currency_table script."
