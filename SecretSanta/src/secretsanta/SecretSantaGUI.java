package secretsanta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class SecretSantaGUI {
    private JFrame frame;
    private JTextArea participantTextArea;
    private JTextField nameField, emailField, senderEmailField;
    private JPasswordField senderPasswordField;
    private JButton addButton, finalizeButton, drawButton;
    private Map<String, String> participants = new HashMap<>();

    public SecretSantaGUI() {
        frame = new JFrame("Secret Santa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        participantTextArea = new JTextArea();
        participantTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(participantTextArea);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Name Label & Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        inputPanel.add(nameField, gbc);

        // Email Label & Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(15);
        inputPanel.add(emailField, gbc);

        // Sender Email Label & Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Sender's Email:"), gbc);
        gbc.gridx = 1;
        senderEmailField = new JTextField(15);
        inputPanel.add(senderEmailField, gbc);

        // Sender Password Label & Field
        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Sender's Password:"), gbc);
        gbc.gridx = 1;
        senderPasswordField = new JPasswordField(15);
        inputPanel.add(senderPasswordField, gbc);

        // Add Participant Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        addButton = new JButton("Add Participant");
        addButton.addActionListener(this::addParticipant);
        inputPanel.add(addButton, gbc);

        // Finalize & Draw Button
        gbc.gridy = 5;
        finalizeButton = new JButton("Finalize & Draw");
        finalizeButton.setEnabled(false);
        finalizeButton.addActionListener(this::drawNames);
        inputPanel.add(finalizeButton, gbc);

        // Send Emails Button
        gbc.gridy = 6;
        drawButton = new JButton("Send Emails");
        drawButton.setEnabled(false);
        drawButton.addActionListener(this::sendEmails);
        inputPanel.add(drawButton, gbc);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void addParticipant(ActionEvent e) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        if (!name.isEmpty() && !email.isEmpty()) {
            participants.put(name, email);
            participantTextArea.append(name + " - " + email + "\n");
            nameField.setText("");
            emailField.setText("");
            finalizeButton.setEnabled(true);
        }
    }

    private void drawNames(ActionEvent e) {
        List<String> names = new ArrayList<>(participants.keySet());
        List<String> shuffledNames = new ArrayList<>(names);

        int maxAttempts = 100;
        boolean validDraw = false;

        while (maxAttempts-- > 0) {
            Collections.shuffle(shuffledNames);
            validDraw = true;

            for (int i = 0; i < names.size(); i++) {
                if (names.get(i).equals(shuffledNames.get(i))) {
                    validDraw = false;
                    break;
                }
            }

            if (validDraw) break;
        }

        if (!validDraw) {
            JOptionPane.showMessageDialog(frame, "Failed to generate valid Secret Santa pairs. Try again!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        participantTextArea.append("\nAssignments:\n");
        for (int i = 0; i < names.size(); i++) {
            participantTextArea.append(names.get(i) + " → " + shuffledNames.get(i) + "\n");
        }

        drawButton.setEnabled(true);
    }

    private void sendEmails(ActionEvent e) {
        String senderEmail = senderEmailField.getText().trim();
        String senderPassword = new String(senderPasswordField.getPassword()).trim();

        if (senderEmail.isEmpty() || senderPassword.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter your email and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String host = "smtp.gmail.com"; // Αντικατάστησε με τον SMTP server σου (π.χ., smtp.gmail.com, smtp.office365.com)

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        List<String> names = new ArrayList<>(participants.keySet());
        List<String> shuffledNames = new ArrayList<>(names);
        Collections.shuffle(shuffledNames);

        for (int i = 0; i < names.size(); i++) {
            String giver = names.get(i);
            String receiver = shuffledNames.get(i);
            String giverEmail = participants.get(giver);

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(senderEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(giverEmail));
                message.setSubject("🎅 Secret Santa Assignment!");
                message.setText("Hello " + giver + ",\n\nYou are the Secret Santa for " + receiver + "! 🎁\n\nHappy Holidays!");

                Transport.send(message);
                participantTextArea.append("Email sent to " + giver + "!\n");

            } catch (MessagingException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SecretSantaGUI::new);
    }
}
