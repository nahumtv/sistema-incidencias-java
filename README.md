## Configuración con Docker

Sigue estos pasos para levantar la base de datos del proyecto con Docker.

### 1. Crear el archivo `.env`

Copia el archivo base de variables de entorno:

```bash
cp env.initial .env
```

Si estás usando PowerShell, también puedes hacerlo así:

```powershell
Copy-Item env.initial .env
```

### 2. Levantar los contenedores

```bash
docker compose up -d
```

Esto levantará el contenedor `postgres-local` usando los valores definidos en `.env`.

### 3. Ejecutar los scripts de base de datos

Una vez que el contenedor esté arriba, ejecuta los scripts SQL en este orden:

```bash
docker exec -i postgres-local psql -U admin -d sgidb < database/001_create_tables.sql
docker exec -i postgres-local psql -U admin -d sgidb < database/002_create_user_base.sql
```

### Variables por defecto

El archivo `env.initial` trae esta configuración inicial:

```env
DB_HOST=localhost
DB_PORT=5432
DB_NAME=sgidb
DB_USER=admin
DB_PASSWORD=password
```

Si necesitas cambiar el puerto, usuario, base de datos o contraseña, edita el archivo `.env` antes de ejecutar `docker compose up -d`.
