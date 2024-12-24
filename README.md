[analytic_report_amazon.postman_collection.json](https://github.com/user-attachments/files/18237354/analytic_report_amazon.postman_collection.json)# Spring Boot RESTful API for Statistics Management

This project is a Spring Boot RESTful API designed to update and manage statistical data stored in MongoDB, with cached responses powered by Redis. The application features user registration and authentication, along with scheduled updates of statistics from a static JSON file (`test_report.json`).

## Features

1. **User Registration**
   - Create a new user account.

2. **User Authentication**
   - Authenticate users with Spring Security.
   - Supports JWT (JSON Web Tokens) for secure authorization.

3. **Statistics Retrieval**
   - Retrieve statistics by date or a range of dates with caching for improved performance.
   - Retrieve statistics by ASIN or a list of ASINs with caching.
   - Retrieve aggregated statistics for all dates with caching.
   - Retrieve aggregated statistics for all ASINs with caching.

4. **Scheduled Data Updates**
   - Automatically update all statistics from the `test_report.json` file every 5 minutes (or at a configurable interval).
   - Detect and update changed data in the database.

5. **Database Initialization**
   - Initialize the database with sample data from the static JSON file `test_report.json`.

## Technical Requirements

- **Backend Framework:** Spring Boot
- **Database:** MongoDB
- **Authentication:** Spring Security (JWT integration for secure token-based authentication is a plus)
- **Caching:** Redis

## Data Source
The application uses a static JSON file (`test_report.json`) to populate and update the database. For more information on the required attributes, refer to the [Amazon SP-API Documentation](https://developer-docs.amazon.com/sp-api/docs/report-type-values-analytics#sales-and-traffic-business-report).

## Installation and Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/spring-boot-api.git
   cd spring-boot-api
   ```

2. Configure the application properties:
   - Update `application.properties` or `application.yml` with your MongoDB and Redis credentials.

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Test the API:
   Use tools like Postman or curl to test endpoints for user registration, authentication, and data retrieval.

## Endpoints Overview

- **POST /register**: Register a new user.
- **POST /login**: Authenticate a user and receive a JWT.
- **GET /statistics?date=YYYY-MM-DD**: Retrieve statistics by date.
- **GET /statistics?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD**: Retrieve statistics for a date range.
- **GET /statistics/asin?asin=ASIN_CODE**: Retrieve statistics by ASIN.
- **GET /statistics/aggregated/dates**: Retrieve aggregated statistics for all dates.
- **GET /statistics/aggregated/asins**: Retrieve aggregated statistics for all ASINs.
- **PUT /statistics/update**: Trigger a manual update of statistics from `test_report.json`.

## Scheduled Tasks
- The application updates the database with new or modified data from the `test_report.json` file every 5 minutes by default. This interval is configurable via the `application.properties` file.

## Dependencies
- Spring Boot
- Spring Security
- JWT
- MongoDB
- Redis
- Jackson (for JSON parsing)

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.
[Uploading analytic_report_amazon.postman_collection.json…]()

