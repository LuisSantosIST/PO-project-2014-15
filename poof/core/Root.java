package poof.core;
import java.util.*;
import poof.textui.exception.*;
/*
*A class Root vai represntar um super utilizador cujas permissoes para executar operacoes sobre ficheiros etc nao vao ser necessarias.
*/
public class Root extends User 
{
	/*
	*construtor:
	*		@param home: directorio home que vai conter o directorio principal da root
	*		@exception EntryUnknownException: lancada pelo metodo getDirectory.
	*		@exception EntryExistsException: lancada pelo metodo createSubDirectory.
	*		@exception IsNotDirectoryException: lancada pelo metodo getDirectory.
	*/
	public Root(Directory home) throws EntryUnknownException, EntryExistsException, IsNotDirectoryException
	{
		super("Super User", "root", home);
	}
	
	/*
	*createNewUser:
	*		@param filesystem: o filesystem onde queremos criar o novo user.
	*		@param name: o nome com que queremos criar o user.
	*		@param username: o username que queremos que ele fique.
	*		@exception UserExistsException se o User existir.
	*		@exception EntryExistsException se ja existir uma entrada com o username do User a ser criado.
	*		@exception EntryUnknownException lancada pelo construtor do User.
	*		@exception IsNotDirecoryException lancada pelo construtor do User.
	*/
	@Override
	@SuppressWarnings("nls")
	public void createNewUser(FileSystem filesystem, String name, String username) 
	throws EntryUnknownException, EntryExistsException, IsNotDirectoryException, UserExistsException
	{	
		if (filesystem.userExists(username))
			throw new UserExistsException(username);
		
		User u = new User(name, username, this.getHomeDirectory().getFather());
		filesystem.putNewUser(u);
	}

	@Override
	@SuppressWarnings("nls")
	public void changeEntryName(Entry e, String s)
	{
		e.rename(s);
	}

	@Override
	@SuppressWarnings("nls")
	public void changeEntryPermissions(Entry e, Boolean b)
	{
			e.changePermission(b);
	}

	@Override
	@SuppressWarnings("nls")
	public void changeEntryOwner(Entry e, User u)
	{
		e.changeOwner(u);
	}

	@Override
	@SuppressWarnings("nls")
	public void writeOnFile(File file, String content)
	{
		file.addContent(content);
	}

	@Override
	@SuppressWarnings("nls")
	public void createDirectory(Directory directory, String name) throws EntryExistsException
	{	
		directory.createSubDirectory(name, this);

	}

	@Override
	@SuppressWarnings("nls")
	public void createNewFile(Directory directory, String name) throws EntryExistsException
	{	
		directory.createFile(name, this);
	}

	@Override
	@SuppressWarnings("nls")
	public void removeEntry(Directory directory, String name)
	throws EntryUnknownException, IllegalRemovalException, IsNotDirectoryException, IsNotFileException
	{
		directory.entryUnknown(name);
  		directory.deleteEntry(name);
  		
	}
}