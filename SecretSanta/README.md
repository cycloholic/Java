# Secret Santa

## 🎁 Description
The **Secret Santa** application is a Java-based GUI tool that helps organize a Secret Santa gift exchange. Users can add participants, randomly assign Secret Santas, and automatically send email notifications to each participant with their assigned recipient.

## ✨ Features
- **Add Participants**: Users can enter names and email addresses.
- **Draw Names**: The program ensures a fair draw where no one gets themselves.
- **Send Emails**: Automatically emails each participant their Secret Santa assignment.
- **Graphical Interface (Swing)**: User-friendly and interactive GUI.

## 🛠 Technologies Used
- Java (Swing for GUI)
- Jakarta Mail API for sending emails

## 📦 Installation & Execution
1. **Clone the Repository**
   ```sh
   git clone https://github.com/USERNAME/SecretSanta.git
   cd SecretSanta
   ```
2. **Install Required Libraries**
   - Add Jakarta Mail API to the classpath (via Maven/Gradle or manually downloading the JAR).

3. **Run the Program**
   ```sh
   javac -d . -cp .:path/to/jakarta-mail.jar secretsanta/SecretSantaGUI.java
   java -cp .:path/to/jakarta-mail.jar secretsanta.SecretSantaGUI
   ```

## 📧 Email Configuration
To send emails, make sure that:
- You use a valid SMTP server (e.g., Gmail: `smtp.gmail.com`).
- You enable "Less secure apps" or generate an App Password for Gmail.

## ⚠️ Important
The program uses basic authentication with a username/password. For better security, consider using OAuth or an App Password instead of your regular password.

## 📜 License
This project is licensed under the MIT License - see the `LICENSE` file for details.

---
🎅 Have fun with Secret Santa! 🎁

## 📸 Screenshots

### Main Interface
![Main Interface](https://github.com/cycloholic/Java/blob/94cfdd2f43a82c220c57b79a93866c726c7e964e/SecretSanta/screenshot_santa.png)

