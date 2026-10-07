# Smart Lost and Found - Backend
Spring Boot 3 + Java 17 + MySQL REST API.

## Run locally
1. Install JDK 17, Maven and MySQL.
2. Create/use database `lost_found` (the URL can create it automatically).
3. Set `DB_USERNAME` and `DB_PASSWORD`, or edit `application.properties`.
4. Run `mvn spring-boot:run`.
5. API: `http://localhost:8080/api`

Default admin: `admin@lostfound.local` / `Admin@123` (change this before production).

## Main endpoints
POST `/api/auth/register`
POST `/api/auth/login`
POST `/api/auth/logout`
GET `/api/items?q=&type=LOST|FOUND`
GET `/api/items/{id}`
POST `/api/items` (Bearer token)
PUT/DELETE `/api/items/{id}` (owner/admin)
PATCH `/api/items/{id}/status?status=...` (admin)
GET `/api/admin/dashboard` (admin)
GET `/api/admin/items` (admin)
