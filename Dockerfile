FROM node:16-alpine
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build-dev
EXPOSE 5000
CMD ["npm", "start"]
