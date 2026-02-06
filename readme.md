demo sencilla:

- database: backend
- user: backend

```sql
create user backend;
create database backend;
alter database backend user backend;
```

para poblarla y tal: `psql -U backend < ./database/init.sql`.

para ejecutarlo: `./backend-core/mvnw spring-boot:run` y `npm run preview`.

esta mal:
- 1 servicio global para todas las entidades

solo lee de la api para cargar las columnas y las tarjetas, no reordena ni hace
nada (de forma persistente). tampoco se pueden crear tarjetas, ni columnas, etc.
para ver más eventos de arrastrar y eso con las consola del navegador.

