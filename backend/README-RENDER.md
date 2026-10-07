# Render Deployment

This backend is prepared for Render using PostgreSQL. The included `render.yaml` creates the web service and PostgreSQL database.

The backend reads `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USERNAME`, and `DB_PASSWORD`, and builds the JDBC URL from them. Render supplies these values from the managed database.

## Important
Change the seeded admin password before using this as a public production system. The simple in-memory token service is suitable for an academic/demo deployment; for a high-security public deployment, replace it with expiring JWTs or a persistent session store.
