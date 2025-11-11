import menu.HomeMenu;
import menu.StartMenu;
import utils.LoadingAnimation;
import utils.Style;

public class StartAdotaPetCLI {
    public static void main(String[] args) {


        // Welcome message with loading animations
        StartMenu startMenu = new StartMenu();
        startMenu.display();

        LoadingAnimation.bar(1000);

        // Display home menu
        HomeMenu homeMenu = new HomeMenu();
        homeMenu.display();





        // System.out.println("Loading dots:");
        // LoadingAnimation.dots(2500);

        // System.out.println("Classic spinner:");
        // LoadingAnimation.spinner(2500);

        // System.out.println("Smooth spinner:");
        // LoadingAnimation.smoothSpinner(2500);


        // System.out.println(Style.msgInfo("Carregando dados do sistema..."));
        // System.out.println(Style.msgSucesso("Pet cadastrado com sucesso!"));
        // System.out.println(Style.msgAviso("Campo 'idade' não preenchido."));
        // System.out.println(Style.msgErro("Falha ao salvar no arquivo."));
    }
}
