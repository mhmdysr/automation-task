# 🛠 Demoblaze E2E Automation Project

This project automates end-to-end test scenarios for the [Demoblaze](https://www.demoblaze.com) e-commerce demo website using Selenium WebDriver and Java.

---

## 📋 Task Scope

This automation covers:

1. ✅ Launching the browser and validating homepage load
2. 🔐 User login with secure credential handling
3. 🔍 Product search and result verification
4. 📄 Product details extraction and validation
5. 🛒 Add to cart and cart content verification
6. 💳 Checkout flow with field validations
7. 📷 Order confirmation pop-up validation and screenshot capture
8. ⚠️ Error handling and recovery using try-catch blocks

---

## 🛠 Setup Instructions

### ✅ Prerequisites

- Java 11+
- Maven
- IntelliJ IDEA
- WebDriver (ChromeDriver)
- Selenium webDriver
- TestNG
- POM Design Pattern
- Internet access

----
### 📂 Project Structure 
src
 └── main
     └── java
         └── pages
             ├── HomePage.java
             ├── ProductPage.java
             ├── UserLoginPage.java
             ├── SearchPahe.java
    └── resources
       └── validLoginCredentials.json

└── test
         └── java
            └── base 
                 ├── BaseTest.java
            └── tests
               ├── HomeTests.java
               ├── ProductTest.java
               ├── UserLoginTests.java
               ├── SearchTests.java
               ├── CheckoutErrorHandlingTests.java
 pom.xml
 README.md


----
📺 **Execution :**  
check [Resourses on Drive](https://drive.google.com/drive/folders/1pMfFCsCeK6WrqiPR8o2hAdUxxIOd-DTF?usp=sharing)

