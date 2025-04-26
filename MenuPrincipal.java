import java.util.Scanner;

public class MenuPrincipal{

    // Códigos ANSI para cores pra ficar bunitinho - no vs code tenho certeza que funciona 
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        // Cabeçalho do TP
        System.out.println(CYAN + "\nTP2 - AEDsIII    ");
        System.out.println("Dupla: João Vitor Mendes e Gabriela Lacerda\n" + RESET);

        // Loop principal do meu Menu
        while (flag) {

            desenharMenuPrincipal();
            int operacao = scanner.nextInt();

            if (operacao == 0) {
                System.out.println("\n" + PURPLE + ">>> Saindo do programa... Até mais!" + RESET);
                desenharPikachu();
                break;
            }

            System.out.print("\nDigite o índice que você gostaria de manipular: ");
            int indice = scanner.nextInt();

            int pesoEstrutura = desenharMenuEstruturas(scanner);

            if (pesoEstrutura == -1) {
                System.out.println(YELLOW + ">>> Operação cancelada! Retornando ao menu principal..." + RESET);
                continue;
            }

            int codigo = operacao + pesoEstrutura;
            executarOperacao(codigo, indice);
        }

        scanner.close();
    }

    private static void desenharPikachu() {
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

    private static void desenharMenuPrincipal() {
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

    private static int desenharMenuEstruturas(Scanner scanner) {
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
    }

    private static void executarOperacao(int codigo, int indice) {
        System.out.print(CYAN + "\nProcessando" + RESET);
        carregarPontos();
    
        switch (codigo) {
            case 2:
                // Chamada da função CREATE em Árvore B
                System.out.println(GREEN + "\n>>> CREATE em Árvore B no índice " + indice + RESET);
                break;
            case 6:
                // Chamada da função CREATE em Hash
                System.out.println(GREEN + "\n>>> CREATE em Hash no índice " + indice + RESET);
                break;
            case 10:
                // Chamada da função CREATE em Lista Invertida
                System.out.println(GREEN + "\n>>> CREATE em Lista Invertida no índice " + indice + RESET);
                break;
            case 3:
                // Chamada da função READ em Árvore B
                System.out.println(BLUE + "\n>>> READ em Árvore B no índice " + indice + RESET);
                break;
            case 7:
                // Chamada da função READ em Hash
                System.out.println(BLUE + "\n>>> READ em Hash no índice " + indice + RESET);
                break;
            case 11:
                // Chamada da função READ em Lista Invertida
                System.out.println(BLUE + "\n>>> READ em Lista Invertida no índice " + indice + RESET);
                break;
            case 4:
                // Chamada da função UPDATE em Árvore B
                System.out.println(YELLOW + "\n>>> UPDATE em Árvore B no índice " + indice + RESET);
                break;
            case 8:
                // Chamada da função UPDATE em Hash
                System.out.println(YELLOW + "\n>>> UPDATE em Hash no índice " + indice + RESET);
                break;
            case 12:
                // Chamada da função UPDATE em Lista Invertida
                System.out.println(YELLOW + "\n>>> UPDATE em Lista Invertida no índice " + indice + RESET);
                break;
            case 5:
                // Chamada da função DELETE em Árvore B
                System.out.println(RED + "\n>>> DELETE em Árvore B no índice " + indice + RESET);
                break;
            case 9:
                // Chamada da função DELETE em Hash
                System.out.println(RED + "\n>>> DELETE em Hash no índice " + indice + RESET);
                break;
            case 13:
                // Chamada da função DELETE em Lista Invertida
                System.out.println(RED + "\n>>> DELETE em Lista Invertida no índice " + indice + RESET);
                break;
            default:
                System.out.println(RED + "\n>>> Código inválido! Retornando ao menu principal..." + RESET);
                break;
        }
    }
    

    private static void carregarPontos() {
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