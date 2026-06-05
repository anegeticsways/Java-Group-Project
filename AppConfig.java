/*
Contributed by Ainin(102810)
Role: Member 4 - Assessment Lead

Description:
1. Acts as a central settings file
2. Contains constants for app dimensions, colors, and fonts to ensure a consistent design across all modules
3. Allows for easy updates to the app's appearance by modifying values in one place
*/

import java.awt.*;

public class AppConfig {
    public static final int PHONE_WIDTH = 390; // Set standard width for mobile app design
    public static final int PHONE_HEIGHT = 700; // Set standard height for mobile app design

    public static final Color BG = new Color(245, 252, 247); // Light green background color
    public static final Color PRIMARY = new Color(34, 139, 94); // Primary color for important elements
    public static final Color TEXT = new Color(40, 40, 40); // Text color for readability

    public static final Font TITLE_FONT = new Font("Segoe UI", Font.BOLD, 22); // Font for titles and headings
    public static final Font NORMAL_FONT = new Font("Segoe UI", Font.PLAIN, 14); // Font for regular text and labels
    public static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14); // Font for buttons to make them stand out
}
