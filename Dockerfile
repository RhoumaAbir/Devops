FROM node:16-alpine

WORKDIR /app


COPY package.json package-lock.json ./

RUN npm install


COPY . .

# Build your application
RUN npm run build-dev

# Expose the port your app runs on
EXPOSE 5000

# Command to run your application
CMD ["npm", "start"]
