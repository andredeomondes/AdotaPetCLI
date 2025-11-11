package menu;

import utils.LoadingAnimation;
import utils.Style;

public class StartMenu {
    public void display() {
        LoadingAnimation.smoothSpinner(1000);
        System.out.println(Style.msgBoasVindas("AdotaPetCLI"));
    }
}
