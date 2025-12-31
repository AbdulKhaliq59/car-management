# Car Management System

A comprehensive car management system with REST API backend and command-line interface for tracking cars and fuel consumption.

## Features

- **Car Management**: Create and list cars with brand, model, and year
- **Fuel Tracking**: Add fuel entries with liters, price, and odometer readings
- **Fuel Statistics**: Calculate fuel consumption and cost statistics
- **REST API**: Full REST API for integration with other applications
- **CLI Tool**: Command-line interface for easy interaction

## Architecture

- **Backend**: Spring Boot REST API (Java 17)
- **CLI**: Java command-line application
- **Build Tool**: Maven multi-module project

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Quick Start

### 1. Clone and Build

```bash
git clone <repository-url>
cd car-management
mvn clean install
```

### 2. Start the Backend

```bash
cd backend
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

### 3. Use the CLI

Open a new terminal and navigate to the CLI directory:

```bash
cd cli
```

## CLI Usage

### Create a Car

```bash
java -cp target/cli-1.0-SNAPSHOT.jar com.example.cli.CarCliApplication create-car --brand Toyota --model Camry --year 2020
```

### List All Cars

```bash
java -cp target/cli-1.0-SNAPSHOT.jar com.example.cli.CarCliApplication list-cars
```

### Add Fuel Entry

```bash
java -cp target/cli-1.0-SNAPSHOT.jar com.example.cli.CarCliApplication add-fuel --carId 1 --liters 45.5 --price 65.75 --odometer 15000
```

### Get Fuel Statistics

```bash
java -cp target/cli-1.0-SNAPSHOT.jar com.example.cli.CarCliApplication fuel-stats --carId 1
```

## Configuration

### Environment Variables

- `CAR_API_BASE_URL`: Backend API URL (default: `http://localhost:8080`)

Example:
```bash
export CAR_API_BASE_URL=http://localhost:8080
```

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/cars` | Create a new car |
| GET | `/api/cars` | List all cars |
| POST | `/api/cars/{id}/fuel` | Add fuel entry |
| GET | `/api/cars/{id}/fuel/stats` | Get fuel statistics |

### API Examples

#### Create Car
```bash
curl -X POST http://localhost:8080/api/cars \
  -H "Content-Type: application/json" \
  -d '{"brand":"Toyota","model":"Camry","year":2020}'
```

#### Add Fuel Entry
```bash
curl -X POST http://localhost:8080/api/cars/1/fuel \
  -H "Content-Type: application/json" \
  -d '{"liters":45.5,"price":65.75,"odometer":15000}'
```

## Running in Different Environments

### Development
```bash
# Terminal 1: Start backend
cd backend && mvn spring-boot:run

# Terminal 2: Use CLI
cd cli && java -cp target/cli-1.0-SNAPSHOT.jar com.example.cli.CarCliApplication list-cars
```

### Production
```bash
# Build JARs
mvn clean package

# Run backend
java -jar backend/target/backend-0.0.1-SNAPSHOT.jar

# Use CLI with custom API URL
CAR_API_BASE_URL=http://your-server:8080 java -cp cli/target/cli-1.0-SNAPSHOT.jar com.example.cli.CarCliApplication list-cars
```

## 📁 Project Structure

```
car-management/
├── backend/                 # Spring Boot REST API
│   ├── src/main/java/
│   │   └── com/example/backend/
│   │       ├── controller/  # REST controllers
│   │       ├── service/     # Business logic
│   │       ├── model/       # Data models
│   │       └── dto/         # Data transfer objects
│   └── pom.xml
├── cli/                     # Command-line interface
│   ├── src/main/java/
│   │   └── com/example/cli/
│   │       ├── CarCliApplication.java
│   │       └── CliUtils.java
│   └── pom.xml
└── pom.xml                  # Parent POM
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License.