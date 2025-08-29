Bajaj Finserv Health | SQL Task (Java – Spring Boot)
Build
mvn clean package -DskipTests


The JAR will be generated in the target/ folder.

Run
java -jar target/bajaj-sql-task-0.0.1-SNAPSHOT.jar

Flow

Calls generateWebhook API with candidate details.

Receives webhook URL and JWT token.

Submits the final SQL query automatically.

Prints the submission response.

Final SQL Query
SELECT e1.emp_id, e1.first_name, e1.last_name, d.department_name,
       (SELECT COUNT(*) FROM employee e2 
        WHERE e2.department = e1.department AND e2.dob > e1.dob) AS younger_employees_count
FROM employee e1
JOIN department d ON e1.department = d.department_id
ORDER BY e1.emp_id DESC;
