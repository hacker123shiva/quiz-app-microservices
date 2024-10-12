# Microservices Project with Spring Boot

This project demonstrates a microservices architecture using Spring Boot, Eureka Service Registry, API Gateway, and OpenFeign for communication between services. The project consists of four main services:

- **Service Registry** (Eureka Server)
- **API Gateway**
- **Question Service** (runs on two instances: `8080` and `8081`)
- **Quiz Service** (runs on `8091`)

## Architecture Overview

1. **Service Registry (Eureka Server)**:

   - The Eureka Server acts as a discovery server where all microservices register themselves.
   - This makes it easier for services to discover and communicate with each other dynamically.
   - **Port**: Default port (8761).

2. **API Gateway**:

   - The API Gateway routes client requests to appropriate services.
   - With Eureka Server, services can be addressed without hard-coding their ports or IP addresses.
   - **Port**: `8765`
   - **Note**: Through the API Gateway, the client does not need to know the individual service port or IP.

3. **Question Service**:

   - The Question Service provides CRUD operations for managing quiz questions.
   - **Port**: `8080`, `8081` (running on two instances for load balancing)

4. **Quiz Service**:
   - The Quiz Service handles the creation and retrieval of quizzes based on available questions.
   - **Port**: `8091`

## Postman API Collections

You can use the following Postman API collections to test the microservices:

- **Question Service:**
  [Postman Collection Link](https://api.postman.com/collections/32429637-3797cfc8-d913-4a07-bd59-0fcc01c26545?access_key=PMAT-01JA0R3JW9AZSMB5X0XXFNC78T)

- **Quiz Service:**
  [Postman Collection Link](https://api.postman.com/collections/32429637-232b6e77-1fa0-4c95-8daf-3c389ea1fd30?access_key=PMAT-01JA0R28TY0V37CVXREYZSFVNN)

- **API Gateway:**
  [Postman Collection Link](https://api.postman.com/collections/32429637-f759aed1-4cb2-4b18-b822-1c3d98cd58a1?access_key=PMAT-01JA0R4ES94AF9C9ZAY1FPRT9R)

You do not need to know the ports or IP addresses of individual services. The **API Gateway** abstracts this and routes the requests to the appropriate services.

## How to Run

1. **Clone the repository** for each microservice.

2. **Build and run each service** in the following order:

   - **Eureka Server**
   - **Question Service**
   - **Quiz Service**
   - **API Gateway**

3. All services will be registered with Eureka Server. You can access the Eureka dashboard at `http://localhost:8761`.

4. Use the **API Gateway** (running on port `8080`) to access the other services without needing their individual ports.

## Technologies Used

- **Spring Boot**
- **Spring Cloud Netflix (Eureka)**
- **Spring Cloud Gateway**
- **Feign Client** (for communication between microservices)
- **Postman** (for API testing)

## Key Annotations Used

- `@EnableEurekaServer`: Enables the Eureka Server for service discovery.
- `@EnableFeignClients`: Enables Feign clients for communication between microservices.
- `@SpringBootApplication`: Main application entry point for each service.
- `@RestController`: Defines RESTful web service endpoints.
- `@Autowired`: For dependency injection.
- `@GetMapping`, `@PostMapping`, `@PutMapping`: Defines HTTP methods for request handling.
- `@FeignClient`,: When combined with Eureka for service discovery, it can automatically route requests to the correct service instance without hardcoding URLs.

## Additional Information

- **Eureka Server:** Used for service registration and discovery.
- **API Gateway:** Routes requests to services based on paths without exposing the internal ports of the services.
- **Feign Client:** Simplifies communication between microservices by abstracting REST calls.
- **Spring Cloud Dependencies:** Used for microservice patterns like service discovery, load balancing, and routing.

---

**Note:**  
Ensure all services are running before making requests via the Postman API collection. The **API Gateway** will handle routing requests to the appropriate microservices.
