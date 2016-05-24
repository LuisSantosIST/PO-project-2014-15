package poof.core;
import java.util.*;
import poof.textui.exception.*;
/*
*A class User vai permitir representar os utilizadores presentes no FileSystem que vao ser responsaveis por um conjunto de directorios
*	e ficheiros sobre o qual vamos querer trabalhar.
*/
public class User implements java.io.Serializable
{	
	//String que vai representar o nome do utilizador.
	private String _name;
	//String que vai representar o username(unico) do utilizador.
	private String _username;
	//Directory correspondente ao directorio principal do user.
	private Directory _homeDirectory;

	/*
	*construtor:
	*		@param name: nome que desejamos dar ao utilizador a ser criado.
	*		@param username: username que queremos dar ao mesmo.
	*		@param home: directorio que vai conter o directorio principal do utilizador.
	*		@exception EntryUnknownException: lancada pelo metodo getDirectory.
	*		@exception EntryExistsException: lancada pelo metodo createSubDirectory.
	*		@exception IsNotDirectoryException: lancada pelo metodo getDirectory.
	*/
	public User(String name, String username, Directory home) 
	throws EntryUnknownException, EntryExistsException, IsNotDirectoryException
	{
		_name = name;
		_username = username;

		home.createSubDirectory(username, this);
		_homeDirectory = home.getDirectory(username);
	}

	/*
	*toString:
	*		@return: retorna a string com que vamos querer representar o utiizador em causa.
	*/
	public String toString()
	{
		return _username + ":" + _name + ":" + _homeDirectory.showAbsolutePath();
	}

	/*
	*getHomeDirectory:
	*		@return: retorna o directorio principal do utilizador em causa.
	*/
	public Directory getHomeDirectory()
	{
		return _homeDirectory;
	}

	/*
	*changeEntryName:
	*		@param e: entrada que pretendemos mudar o nome.
	*		@param s: string com o nome que lhe pretendemos dar.
	*/
	public void changeEntryName(Entry e, String s) throws AccessDeniedException
	{
		if(e.getOwner().equals(this) || e.getPermission())
		{
			e.rename(s);
		}
		else throw new AccessDeniedException(_username);
	}

	/*
	*changeEntryPermissons:
	*		@param e: entrada que pretendemos mudar a permissao.
	*		@param b: valor logico(true ou false) que queremos que ela adopte.
	*/
	public void changeEntryPermissions(Entry e, Boolean b) throws AccessDeniedException
	{
		if(e.getOwner().equals(this))
		{
			e.changePermission(b);
		}
		else throw new AccessDeniedException(_username);
	}

	/*
	*createNewUser:
	*		@exception AccessDeniedException: lanca excepcao se algum utilizador sem ser a root tentar criar um User.
	*/
	public void createNewUser(FileSystem filesystem, String name, String username) 
	throws AccessDeniedException, EntryUnknownException, EntryExistsException, IsNotDirectoryException, UserExistsException
	{
		throw new AccessDeniedException(_username);
	}
	
	/*
	*changeEntryOwner:
	*		@param e: entrada que queremos mudar o utilizador.
	*		@param u: user que pretendemos que seja o novo responsavel pela entrada.
	*/
	public void changeEntryOwner(Entry e, User u) throws AccessDeniedException
	{
		if(e.getOwner().equals(this)) 
		{
			e.changeOwner(u);
		}
		else throw new AccessDeniedException(_username);
	}

	/*
	*writeOnFile:
	*		@param file: ficheiro sobre o qual queremos escrever.
	*		@param content: conteudo que queremos escrever.
	*		@exception AccessDeniedException: se o utilizador em questao nao tiver permissoes.
	*/
	public void writeOnFile(File file, String content) throws AccessDeniedException
	{
		if (file.getOwner().equals(this) || file.getPermission())
			file.addContent(content);

		else
			throw new AccessDeniedException(_username);
	}

	/*
	*createDirectory:
	*		@param directory: directorio onde queremos criar um subdirectorio.
	*		@param name: nome do directorio que queremos criar.
	*		@exception AccessDeniedException: caso o utilizador nao tenha permissao para criar um directorio nesse directorio.
	*		@exception EntryExistsException: caso ja exista uma entrada com esse nome.
	*/
	public void createDirectory(Directory directory, String name) throws AccessDeniedException, EntryExistsException
	{	
		directory.entryExists(name);
		if (directory.getOwner().equals(this) || directory.getPermission())
			directory.createSubDirectory(name, this);

		else
			throw new AccessDeniedException(_username);
	}

	/*
	*createNewFile:
	*		@param directory: directorio onde queremos criar um ficheiro.
	*		@param name: nome do ficheiro que queremos criar.
	*		@exception AccessDeniedException: caso o utilizador nao tenha permissao para criar um file nesse directorio.
	*		@exception EntryExistsException: caso ja exista uma entrada com esse nome.
	*/
	public void createNewFile(Directory directory, String name) throws AccessDeniedException, EntryExistsException
	{	
		directory.entryExists(name);
		if (directory.getOwner().equals(this) || directory.getPermission())
			directory.createFile(name, this);

		else
			throw new AccessDeniedException(_username);
	}

	/*
	*removeEntry:
	*		@param directory: directorio da entrada que queremos remover.
	*		@param name: nome da entrada que queremos remover.
	*		@exception EntryUnknownException: lancada caso a entrada nao exista.
	*		@exception AccessDeniedException: caso o utilizador nao tenha permissao.
	*		@exception IllegalRemovalException: caso se tente tirar o "." ou o "..".
	*		@exception IsNotDirectoryException: lancada pelo metodo getDirectory.
	*		@exception IsNotFileException: lancada pelo metodo getFile.
	*/
	public void removeEntry(Directory directory, String name)
	throws EntryUnknownException, AccessDeniedException, IllegalRemovalException, IsNotDirectoryException, IsNotFileException
	{
		directory.entryUnknown(name);

    	Entry entrada = directory.getEntry(name);

  		if ((entrada.getOwner().equals(this) && directory.getOwner().equals(this)) 
  			|| (entrada.getPermission() && directory.getPermission()))

  			directory.deleteEntry(name);

  		else
  			throw new AccessDeniedException(_username);
	}

	/*
	*equals:
	*		@param u: user que pretendemos usar para fazer a comparacao.
	*		@return: valor logico true em caso de terem o mesmo username(id) false caso contrario.
	*/
	public Boolean equals(User u)
	{
		return _username.equals(u.getUsername());
	}

	/*
	*getName:
	*		@return: retorna a string correspondente ao nome do utilizador.
	*/
	public String getName()
	{
		return _name;
	}

	/*
	*getUsername:
	*		@return: retorna a string correspondente ao Username(id) do utilizador.
	*/
	public String getUsername()
	{
		return _username;
	}

}