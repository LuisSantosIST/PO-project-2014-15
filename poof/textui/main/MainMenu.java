package poof.textui.main;

import poof.core.*;
import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.Menu;
import static pt.utl.ist.po.ui.UserInteraction.IO;

// FIXME: import project-specific classes

/**
 * Represents the main menu of this apllication. This is the first menu
 * shown to the users.
 ***/
public class MainMenu extends Menu {
  /**
   * Constructor
   **/
  public MainMenu(ActualState as) {
    super(poof.textui.main.MenuEntry.TITLE, new Command<?>[] {
	    new New(as),
	      new Open(as),
	      new Save(as),
	      new Login(as),
	      new ShowMenuShell(as), //vai receber mesmo um sf?
	      new ShowMenuUser(as),
	      });
  }

  /**
   * Hide options when the application does not have a file system.
   **/
  public void hideOptionsEmpty() {
    entry(2).invisible();
    entry(3).invisible();
    entry(4).invisible();
    entry(5).invisible();
  }

  /**
   * Show hidden options when the application has a file system.
   **/
  public void showOptionsNonEmpty() {
    entry(2).visible();
    entry(3).visible();
    entry(4).visible();
    entry(5).visible();
  }
}
