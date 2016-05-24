package poof.textui;

import java.util.*;
import poof.core.*;
import poof.textui.main.MainMenu;
import pt.utl.ist.po.ui.Command;
import pt.utl.ist.po.ui.InvalidOperation;
import pt.utl.ist.po.ui.Menu;
import static pt.utl.ist.po.ui.UserInteraction.IO;

/**
 * Forms editor application: allows the creation and manipulation of
 * graphical forms such as squares, circles, and lines.
 * Forms can be created, moved, and deleted.
 * 
 * @author Programação com Objectos
 * @version 3.0
 */
public class Shell {

    /**
     * @param args command line arguments.
     */
    public static void main(String args[]) throws InvalidOperation
    {

	ActualState as = new ActualState();
	FileSystem filesystem = as.getFileSystem();
	MainMenu menu = new MainMenu(as);
	menu.hideOptionsEmpty();


	String filename = java.lang.System.getProperty("import");

	if (filename != null)
	{
		ParseFile p = new ParseFile();
		as.changeActualFileSystem(p.parse(filename));
		as.changeActualUser(as.getFileSystem().getRoot());
		as.changeActualDirectory(as.getActualUser().getHomeDirectory());
		menu.showOptionsNonEmpty();
	}

	menu.open();
	IO.close();
    }
}
