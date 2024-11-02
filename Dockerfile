FROM node:16-alpine

# Set the working directory
WORKDIR /app

# Copy package.json and package-lock.json first to leverage Docker cache
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of your application code
COPY . .

# Build your application
RUN npm run build-dev

# Expose the port your app runs on
EXPOSE 5000

# Command to run your application
CMD ["npm", "start"]
