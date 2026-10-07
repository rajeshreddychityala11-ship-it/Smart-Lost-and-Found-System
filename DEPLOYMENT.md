# Smart Lost & Found — Vercel + Render Deployment

## Architecture
- Frontend: Vercel (static HTML/CSS/JavaScript)
- Backend: Render (Spring Boot + Docker)
- Database: Render PostgreSQL

## 1. Put the project in GitHub
Upload the project repository containing `backend`, `frontend`, and `render.yaml`.

## 2. Deploy backend on Render
In Render, choose **New → Blueprint** and select the GitHub repository. Render reads `render.yaml`, creates the PostgreSQL database and the Docker web service.

Wait until the web service is live. Copy its HTTPS URL, such as `https://smart-lost-found-api.onrender.com`.

## 3. Deploy frontend on Vercel
Import the same GitHub repository in Vercel and set **Root Directory** to `frontend`.
Set environment variable:
`API_BASE_URL=https://YOUR-RENDER-SERVICE.onrender.com/api`

Build command: `npm run build`
Output directory: `.`

Deploy.

## 4. Test
Open the Vercel URL. Register a user, log in, report an item, search reports, and test the admin dashboard.

Default admin for the academic project:
- Email: admin@lostfound.local
- Password: Admin@123

Change this password before public use.

## Security note
The current project uses an in-memory token service for simplicity. It is acceptable for an academic/demo deployment but should be replaced with expiring JWTs or a persistent session store for a serious public production system.
