# UniApp 🎓

UniApp is a Java desktop application that allows users to search for universities worldwide, fetch their details from an API, and manage university data using a local database.

## ✨ Features
- 🌍 **Search for universities** by name or country using an external API.
- 🖥️ **Graphical User Interface (GUI)** built with Swing.
- 🗄️ **Database integration** using JPA (Java Persistence API) and an embedded database.
- 📊 **Statistics generation** – track university views and export data to PDF.
- 🔗 **REST API integration** using OkHttp for fetching university data.

## 🛠️ Technologies Used
- **Java** (JDK 11+)
- **Swing** (for GUI)
- **JPA (Java Persistence API)** for database interaction
- **OkHttp** for API requests
- **Gson** for JSON parsing
- **iText PDF** for exporting reports
- **NetBeans GUI Builder** 
 
🏛 Data Source
The application fetches university data from the Hipolabs API:
http://universities.hipolabs.com/search?name=university_name

## 📥 Installation & Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/cycloholic/Java/UniApp.git
   cd Java/UniApp
   ## 🛠 Database Setup (Apache Derby)

UniApp uses **Apache Derby (Java DB)** as a local database to store university data retrieved from an external API. The database is automatically created when you run the application.

### **➡️ How to Set Up Derby (if needed)**
1. **Download Apache Derby** if not already installed:
   - Derby comes pre-installed with NetBeans. If you're using NetBeans, no extra setup is required.
   - Otherwise, you can download it from [Apache Derby](https://db.apache.org/derby/).

2. **Ensure the Derby Server is Running**
   - If using NetBeans:
     - Go to `Services` → `Databases`
     - Right-click on `Java DB` → `Start Server`
   - If using command line:
     ```sh
     java -jar derbyrun.jar server start
     ```

3. **Database Connection**
   - The application connects to the database using:
     ```
     jdbc:derby://localhost:1527/UniApp
     ```
   ## 🛠 Database Setup (Apache Derby)

UniApp uses **Apache Derby (Java DB)** as a local database to store university data retrieved from an external API. You need to **manually create the database and tables** before running the application.

### **➡️ How to Set Up Derby**
1. **Download Apache Derby** (if not already installed):
   - Derby comes pre-installed with NetBeans. If you're using NetBeans, no extra setup is required.
   - Otherwise, you can download it from [Apache Derby](https://db.apache.org/derby/).

2. **Ensure the Derby Server is Running**
   - If using NetBeans:
     - Go to `Services` → `Databases`
     - Right-click on `Java DB` → `Start Server`
   - If using the command line:
     ```sh
     java -jar derbyrun.jar server start
     ```

3. **Create the Database Manually**
   - Open the **SQL Command Line** in NetBeans or connect via another Derby client.
   - Run the following SQL commands to create the database and tables:

   ```sql
   CREATE TABLE UNIAPP (
       NAME VARCHAR(255) PRIMARY KEY,
       WEB_PAGE VARCHAR(255) NOT NULL,
       STATE VARCHAR(255) NOT NULL,
       CODE VARCHAR(10) NOT NULL,
       COUNTRY VARCHAR(100) NOT NULL,
       DOMAIN VARCHAR(255) NOT NULL,
       PHONE VARCHAR(20),
       VIEWS INT DEFAULT 0 NOT NULL,
       COMMENTS VARCHAR(500)
   );


4. **Run the Application**
   ```sh
   java -cp ".;libs/*" UniApp.UniApp_JFrame


## 📸 Screenshots

### Main Interface
![Main Interface](https://github.com/cycloholic/Java/blob/fb346d46a27cfb9cace15d276fde6e043e6444e6/UniApp/screenshot_main.png)


### Statistics Window
![Statistics](https://github.com/cycloholic/Java/blob/134988175e4eddb074e485169143aad27cb48b7a/UniApp/screenshot_stats.png)


