# Registration fix

This backend keeps the existing Render/PostgreSQL configuration and fixes the registration path.

Changes:
- validates name, email, and password on the server
- normalizes email to lowercase
- checks duplicate email before saving
- uses `saveAndFlush()` so database errors are caught during registration
- returns a clear 400/409 error instead of an unexplained 500 for common registration conflicts
- prints unexpected exceptions to Render logs while still returning a safe API response

## Deploy
1. Replace the backend files in the GitHub repository with this `backend` folder.
2. Commit and push to `main`.
3. Render will automatically rebuild the backend if Auto Deploy is enabled.
4. Wait for `Started LostFoundApplication` in the Render logs.
5. Open the Vercel registration page and register with a new email.
