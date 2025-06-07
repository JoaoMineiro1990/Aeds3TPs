package Classes.Menu;

import java.util.Scanner;

public class Desenhar {
    
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";

    public static void desenharPikachu() {
        System.out.println(YELLOW + "\n");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣷⣦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣶⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⡿⠿⠿⠿⠿⢿⣷⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⣄⡀⠀⠀⠀⠀⠉⠙⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣦⡀⠀⠀⠀⠀⠀⠹⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠿⣷⣤⣄⠀⠀⠀⢿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣴⠾⠿⠗⠒⠒⠈⠙⠻⢶⣤⣄⣻⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣤⣤⣤⣴⣶⣤⣤⣤⣤⠀⣠⣴⠿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⢀⣠⣶⠘⣿⠟⠛⠉⠉⠁⠀⠀⠀⠀⠀⢠⡤⠞⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⢀⣠⣴⣾⣿⣿⣿⣷⣤⡀⠀⠀⠀⠀⠀⠀⠀⣀⡄⣼⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠘⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣦⣄⣀⡴⠖⠛⠉⣸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣴⣦⣤⡀⠸⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠈⠉⠛⠛⠛⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⣿⡇⠀⠀⢀⣀⣠⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣯⣠⣿⣿⡇⠀⢻⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⡞⢻⣇⠀⣴⣿⣿⡋⣻⡇⠀⠀⠀⢠⣤⡶⠀⠀⠀⠀⠘⣿⣿⣿⡿⠃⠀⠈⢿⡄⠀⠀⠀⢀⣼⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⡶⠿⠛⠉⠁⠀⠀⣿⠀⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⢨⠀⠀⠀⠀⠀⠀⣀⠋⠉⠀⢀⣴⣶⣾⣿⢀⣠⣶⣿⣿⣷⣧⣆⣠");
        System.out.println("⠀⠀⠀⠀⠀⢀⣠⣤⣶⠾⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠿⢀⡈⠛⠛⠛⠁⠀⠀⠀⢀⣰⣿⣷⣶⣦⣴⠖⠚⠉⠀⠀⠀⣿⣿⣿⣿⣿⠘⠋⠁⢸⣿⣿⣿⣿⠏");
        System.out.println("⠀⢠⣤⣶⠿⠟⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣷⡄⠀⠀⠘⠛⣿⣿⠛⠉⠉⠉⠙⡆⠀⠀⠀⠀⠀⣿⣿⣿⣿⡏⠀⠀⠀⠈⠉⠁⣿⡟⠀");
        System.out.println("⠀⠈⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠘⡇⠀⠀⠀⠀⣸⠇⠀⠀⠀⠀⠀⠘⠻⠿⣋⠀⠀⠀⠀⠀⠀⣼⡿⠁⠀");
        System.out.println("⠀⠀⠸⣟⣁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡛⢿⣿⣿⡿⠃⠀⠀⠀⠀⠀⠙⢦⡀⠀⢠⡟⠀⠀⠀⠀⠀⠀⠀⢀⣴⠏⠀⠀⠀⠀⠀⣰⣿⠃⠀⠀");
        System.out.println("⠀⠀⠀⢿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⠞⠋⣿⡷⠀⠻⢯⣁⠀⠀⠀⠀⠀⠀⠀⠀⠙⢦⣼⠇⠀⠀⠀⠀⠀⢀⣴⡿⠋⠀⠀⠀⠀⠀⣰⣿⠏⠀⠀⠀");
        System.out.println("⠀⠀⠀⠘⣿⡆⠀⠀⠀⠀⢀⣠⣴⣾⠟⠋⠁⠀⢰⣿⡇⠀⠀⢷⡌⠓⠦⢤⣀⠀⠀⠀⠀⠀⠀⠛⠀⠀⠀⠀⠀⠐⠛⠁⠀⠰⣾⠀⠀⠀⣰⣿⠏⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⢹⣷⣀⣠⣴⣾⣿⠟⠋⠀⠀⠀⠀⠀⣾⡿⠀⠀⠀⢸⡇⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠀⢀⣼⣿⠋⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⣿⣿⣿⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⣿⣧⣄⡀⠀⢸⠃⠀⠀⠀⠀⠀⠰⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣇⠿⠛⠁⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠘⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠿⠿⠿⠿⠧⣿⠀⠀⠀⠀⠀⠀⠀⠈⠻⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠘⣿⣄⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡟⠄⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠓⠶⣷⣤⣀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⠁⠀⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣧⡀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⠃⠀⠀⠘⢷⣄⠀⠀⠀⠀⠀⠀⠀⠈⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⢿⣴⣦⡀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢀⠀⢀⣠⣾⡇⠀⠀⠀⠀⠈⠛⢷⣦⡀⠀⠀⢠⣤⣤⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⠛⢽⣽⡇⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣻⣷⢶⣬⡛⠃⠀⠀⠀⠀⠀⠀⠀⠙⠻⢷⣤⣼⣿⣿⠿⠙⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠾⠋⠀⠀⢈⣾⠇⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⡝⠦⠉⠻⠷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡇⠀⠀⠀⣠⡿⠁⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⠃⢀⣠⡾⠋⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⣦⣀⠀⠀⢀⡀⠀⠀⠀⠀⢀⣀⣀⣠⣤⣤⣴⠶⠶⠶⠶⠶⠶⠦⠤⢸⣿⣷⣶⡿⠋⡄⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠙⢿⣷⣄⣘⣷⠀⠴⠶⠟⠛⠛⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⢁⣠⣶⣿⡀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠃⠀⠀⠉⠻⠿⠿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣶⣿⣿⡿⢟⣃⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣴⣶⣦⣄⠀⠀⠀⣀⣤⣶⣿⣿⡿⠟⠉⠀⢸⡏⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⠿⣛⣛⡻⢿⣷⣶⣿⣿⣿⠿⠋⠁⠀⠀⠀⠀⣼⠁⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣛⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⣤⣿⣿⠃⣾⣿⣿⣿⠀⣿⣿⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⣿⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⠻⢿⣿⠟⣰⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡿⠿⠿⠿⠿⠿⠿⠛⠛⠛⠛⠙⠻⢿⣷⣶⣶⣿⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡿⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⠃⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣶⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡿⠃⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠿⣦⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣴⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⢿⣶⣦⣄⣀⣀⣀⣀⣀⣀⣀⣤⣴⣾⣿⠿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠛⠛⠛⠛⠛⠛⠛⠛⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
        System.out.println(RESET + "");
    }

    public static void desenharMenuPrincipal() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║              MENU PRINCIPAL               ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║ 1 - CASAMENTO DE PADRÕES                  ║");
        System.out.println("║ 2 - COMPRESSÃO DE DADOS                   ║");
        System.out.println("║ 0 - SAIR                                  ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
    }

    /* 
    public static void desenharMenuPrincipalCRUD() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║           MENU PRINCIPAL - CRUD           ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║ 1 - CREATE                                ║");
        System.out.println("║ 2 - READ                                  ║");
        System.out.println("║ 3 - UPDATE                                ║");
        System.out.println("║ 4 - DELETE                                ║");
        System.out.println("║ 0 - SAIR                                  ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
    }

    
    public static int desenharMenuEstruturas(Scanner scanner) {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║         ESCOLHA A ESTRUTURA DE DADOS      ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║ 1 - Árvore B                              ║");
        System.out.println("║ 2 - Hash                                  ║");
        System.out.println("║ 3 - Lista Invertida                       ║");
        System.out.println("║ 0 - Cancelar operação                     ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.print("Escolha uma estrutura: ");

        int estrutura = scanner.nextInt();

        switch (estrutura) {
            case 1:
                return 1; // Peso da Árvore B
            case 2:
                return 5; // Peso da Hash
            case 3:
                return 9; // Peso da Lista Invertida
            case 0:
                return -1; // Cancelar
            default:
                System.out.println(RED + ">>> Estrutura inválida! Retornando ao menu principal..." + RESET);
                return -1;
        }
    }*/

    public static void desenharMenuCompressao() {
        System.out.println("\n╔═══════════════════════════════════════════╗");
        System.out.println("║         MENU - COMPRESSÃO DE DADOS        ║");
        System.out.println("╠═══════════════════════════════════════════╣");
        System.out.println("║ 1 - HUFFMAN                               ║");
        System.out.println("║ 2 - LZW                                   ║");
        System.out.println("║ 3 - DESCOMPRIMIR                          ║");
        System.out.println("║ 0 - SAIR                                  ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");
    }
    
    public static void desenharMenuCasamentoPadroes() {
    System.out.println("\n╔═══════════════════════════════════════════╗");
    System.out.println("║         MENU - CASAMENTO DE PADRÕES       ║");
    System.out.println("╠═══════════════════════════════════════════╣");
    System.out.println("║ 1 - KMP                                   ║");
    System.out.println("║ 2 - BOYER-MOORE                           ║");
    System.out.println("║ 0 - SAIR                                  ║");
    System.out.println("╚═══════════════════════════════════════════╝");
    System.out.print("Escolha uma opção: ");
}


    public static void carregarPontos() {
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
            System.out.println();
        } catch (InterruptedException e) {
            System.out.println("Erro no carregamento.");
        }
    }
}