/*
  change player's password something to a player.
*/

package ScorchServer.ServerShell.commands;

import java.util.Vector;
import ScorchServer.*;
import ScorchServer.ServerShell.ServerShell;
import scorch.*;

public class passwd extends shellCommand
{
    static Game g;
    static PlayerProfile p;
    public static String help = "To reset a password for a user.";

    //arguments are ignored here for now.
    public static void run(Vector args, Object owner)
    {
	ServerShell shell = (ServerShell)owner;
	String password = "";

	if ( args.size() == 0 )
	    {
		shell.println("Usage: passwd PlayerName [password]");
		return;
	    }
	
	try
	    {
		p = ScorchServer.findProfileByName
		    ((String)args.elementAt(0));
		
		for (int i = 1; i < args.size(); i++)
		    password = password + args.elementAt(i);
		
		if (p == null)
		    shell.println("Player " + (String)args.elementAt(0)
				  +" does not exist.\n");
		else
		    {
			System.out.println("Password-" + password+ "-");
			p.setPassword(password);
			p.encrypt();
			ScorchServer.changeProfile(p);
		    }
	    }
	catch( Exception e )
	    {
		shell.println("setPasswd failed: " + e );
	    }
    }

}
