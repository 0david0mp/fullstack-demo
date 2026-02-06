demo sencilla:

- database: backend
- user: backend

para poblarla y tal: `psql -U backend < init.sql`

solo lee de la api para cargar las columnas y las tarjetas, no reordena ni hace
nada. tampoco se pueden crear tarjetas, ni columnas, etc.
