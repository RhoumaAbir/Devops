# Use Node.js 16 Alpine image as the base
FROM node:16-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy only package.json and package-lock.json to the container, then install dependencies
COPY package.json package-lock.json /app/
RUN npm install

# Copy the rest of the application code
COPY . /app

# Build the application if required
RUN npm run build-dev

# Expose the application port
EXPOSE 5000

# Start the application
CMD ["npm", "start"]
