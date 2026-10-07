# Smart Lost and Found - Frontend
Plain HTML/CSS/JavaScript frontend. No build step is required.

## Local use
Run the backend on port 8080, then serve this folder through a static server (recommended) or any static hosting provider.
The frontend defaults to `http://localhost:8080/api`.
To point it elsewhere, run in the browser console once:
`localStorage.setItem("apiBase","https://YOUR-BACKEND-DOMAIN/api")`
then refresh.

Do not open pages with `file://` in production; deploy this folder to Netlify, Vercel static hosting, GitHub Pages, Nginx, or similar.
