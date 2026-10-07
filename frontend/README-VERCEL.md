# Vercel Deployment

1. Import this `frontend` folder/repository into Vercel.
2. Set the Vercel environment variable `API_BASE_URL` to your Render backend URL followed by `/api`, for example:
   `https://smart-lost-found-api.onrender.com/api`
3. Build command: `npm run build`
4. Output directory: `.`
5. Deploy.

The build script writes `js/config.js` with the Render API URL.
