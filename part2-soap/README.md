# Part 2 - SOAP Webservice (JAX-WS / Apache CXF)

This part extracts the inventory service from the monolith and exposes it as a SOAP webservice with a formal WSDL contract.

## How to Run

1. Ensure PostgreSQL is running:
   ```bash
   docker compose up -d
   ```

2. Build the project:
   ```bash
   mvn clean compile
   ```

3. Start the SOAP service:
   ```bash
   mvn exec:java -Dexec.mainClass="com.datamart.Main"
   ```

4. Access the WSDL:
   Open `http://localhost:8080/inventory?wsdl` in your browser to view the contract.

## Testing with Postman

### Test createProduct

**URL:** `http://localhost:8080/inventory`  
**Method:** POST  
**Header:** `Content-Type: text/xml;charset=UTF-8`

**Body:**
```xml
<soapenv:Envelope
xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
xmlns:inv="http://service.datamart.com/">
<soapenv:Header/>
<soapenv:Body>
<inv:createProduct>
<id>1</id>
<name>Laptop</name>
<quantity>10</quantity>
<price>1299.99</price>
</inv:createProduct>
</soapenv:Body>
</soapenv:Envelope>
```

### Test getProduct

```xml
<soapenv:Envelope
xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
xmlns:inv="http://service.datamart.com/">
<soapenv:Header/>
<soapenv:Body>
<inv:getProduct>
<id>1</id>
</inv:getProduct>
</soapenv:Body>
</soapenv:Envelope>
```

### Test listProducts

```xml
<soapenv:Envelope
xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
xmlns:inv="http://service.datamart.com/">
<soapenv:Header/>
<soapenv:Body>
<inv:listProducts/>
</soapenv:Body>
</soapenv:Envelope>
```

### Test updateProduct

```xml
<soapenv:Envelope
xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
xmlns:inv="http://service.datamart.com/">
<soapenv:Header/>
<soapenv:Body>
<inv:updateProduct>
<id>1</id>
<name>Updated Laptop</name>
<quantity>5</quantity>
<price>1399.99</price>
</inv:updateProduct>
</soapenv:Body>
</soapenv:Envelope>
```

### Test deleteProduct

```xml
<soapenv:Envelope
xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
xmlns:inv="http://service.datamart.com/">
<soapenv:Header/>
<soapenv:Body>
<inv:deleteProduct>
<id>1</id>
</inv:deleteProduct>
</soapenv:Body>
</soapenv:Envelope>
```

## Unit Tests

Run the test suite:
```bash
mvn test
```

The tests cover:
- Create and retrieve product
- Duplicate ID rejection
- Invalid quantity validation
- Invalid price validation
- Empty name validation
- Update product
- Delete product
- List products
- Non-existent product retrieval

## Reflection Question

### How does the WSDL contract enforce structure? Compare the explicit typing in SOAP with the free-form interface of your monolithic app.

**WSDL Contract Enforcement:**

1. **Strict Type Definitions**: WSDL explicitly defines input and output types for every operation. The XML Schema embedded in WSDL specifies:
   - Exact parameter names and types (int, double, string)
   - Required vs optional fields
   - Min/max occurrences
   - Valid value ranges

2. **Self-Documenting**: The WSDL serves as a machine-readable contract that clients can consume via tools like SoapUI or code generators. There's no ambiguity about what parameters are required or what format the response will be in.

3. **Validation at the Protocol Level**: SOAP engines validate XML against the WSDL schema before routing to service implementation. Invalid requests are rejected with a SOAP Fault before business logic executes.

4. **Version Compatibility**: Changes to the service interface require WSDL updates. This allows clients to detect breaking changes.

**Comparison with Monolithic App:**

- **Free-form Interface**: The console application has a loose, unstructured interface. Menu options are hard-coded strings, and input validation happens only inside business logic.
- **No Contract**: There's no formal specification of what the application accepts or returns. Users must read documentation or source code to understand how to use it.
- **Tight Coupling**: Changes to validation rules or field names directly affect all users interacting with the code.
- **Limited Reusability**: Only Java code running in the same JVM can directly access the service. External systems have no standard way to integrate.

**Key Advantage of WSDL:**

WSDL enables **loose coupling** and **interoperability**. Enterprise systems written in different languages (Python, C#, Node.js) can automatically generate client code from the WSDL and communicate without any source code sharing. This is essential for Large distributed systems where multiple teams and vendors need to integrate.