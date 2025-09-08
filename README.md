# FlightSearch

A full-stack flight search application that integrates with Amadeus API to provide real-time flight offers and airport location search capabilities. The project consists of a Spring Boot backend API and a React TypeScript frontend built with Vite.

## Prerequisites

Before you begin, ensure you have the following installed on your system:

- **Docker** and **Docker Compose** (recommended for quick setup)
- **Java 21** or higher (for local development)
- **Node.js 22** or higher (for local frontend development)
- **Git** for cloning the repository

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/egvr2002/FlightSearch
cd FlightSearch
```

## Running with Docker

The easiest way to run the entire application is using Docker Compose, which will start both the backend and frontend services.

### Prerequisites for Docker

Make sure you have Docker and Docker Compose installed on your system.

### Environment Setup

Before running the application, you need to create environment files for both services:

#### Backend Environment

Create a `.env` file in the `backend/` directory with the following variables:

```bash
# backend/.env
AMADEUS_API_KEY=your_amadeus_api_key_here
AMADEUS_API_SECRET=your_amadeus_api_secret_here
AMADEUS_BASE_URL=https://test.api.amadeus.com
```

#### Frontend Environment

Create a `.env` file in the `frontend/` directory with the following variables:

```bash
# frontend/.env
VITE_API_BASE_URL=http://localhost:8080/api
```

### Run the Application

```bash
docker compose up --build -d
```

The application will be available at:

- **Frontend**: http://localhost:4173
- **Backend API**: http://localhost:8080

### Stop the Application

```bash
docker compose down
```

## Running Tests

### Backend Tests

```bash
cd backend

# Using Gradle wrapper
./gradlew test

# Using local Gradle installation
gradle test
```

### Frontend Tests

```bash
cd frontend

# Run tests
npm test

# Run with coverage
npm run test:coverage
```

## Development

### Technologies

#### Backend

- **Spring Boot 3.5.4** - Main framework
- **Java 21** - Programming language
- **Gradle** - Build tool and dependency management
- **Spring WebFlux** - Reactive web framework for API calls
- **Spring Cache** with **Caffeine** - Caching layer
- **MapStruct** - Object mapping
- **Lombok** - Reducing boilerplate code
- **JUnit 5** - Testing framework

#### Frontend

- **React 19** - UI framework
- **TypeScript** - Type-safe JavaScript
- **Vite** - Build tool and dev server
- **React Router v7** - Routing
- **Tailwind CSS v4** - Utility-first CSS framework
- **Radix UI** - Accessible UI components
- **Axios** - HTTP client
- **Zod** - Schema validation
- **Lucide React** - Icon library

### API Integration

The application integrates with the **Amadeus Travel API** to provide:

- Real-time flight search and pricing
- Airport and location search
- Airline information
- Flight details and amenities

### Docker Services

The `docker-compose.yaml` defines two services:

- **flight-search-be**: Backend Spring Boot application (port 8080)
- **flight-search-fe**: Frontend React application (port 4173)

Both services are configured with automatic restart on failure and depend on their respective environment files.
