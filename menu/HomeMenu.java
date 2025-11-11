package menu;

import utils.Style;

public class HomeMenu {
    public void display() {
        System.out.println(Style.msgInfo("[1] Register a new pet"));
        System.out.println(Style.msgInfo("[2] Delete a registered pet"));
        System.out.println(Style.msgInfo("[3] List all registered pets"));
        System.out.println(Style.msgInfo("[4] List pets by criteria"));
        System.out.println(Style.msgInfo("[5] Log out"));
    }

}
