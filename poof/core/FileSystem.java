
package poof.core;
import java.util.*;
import poof.textui.exception.EntryExistsException;
import poof.textui.exception.EntryUnknownException;
import poof.textui.exception.IsNotDirectoryException;
import poof.textui.exception.UserUnknownException;
/*
*A class FileSystem representa um sistema de ficheiros que vai conter um conjunto de utilizadores identificados pelo seu id (int).
*	o sistema de ficheiros vai permitir efectuar operacoes de manipulacao sobre cada utilizador.
*/
public class FileSystem implements java.io.Serializable
{
	//Map que vai guardar os diversos utilizadores representados por um id unico.
	private Map<String, User> _users = new TreeMap<String, User>();
	//directorio que vai ser o directorio raiz de toda a arvore do FileSystem (head).
	private Directory _rootDirectory = new Directory("/", null);

	/*
	*Construtor:
	*		vai criar um directorio chamado home.
	*		inicializar a root e guarda-la no Map.
	*		mudar o dono do directorio raiz e do directorio home para a root.
	*/
	public FileSystem() throws EntryExistsException, EntryUnknownException, IsNotDirectoryException
	{
		_rootDirectory.createSubDirectory("home", null);
		Root root = new Root(_rootDirectory.getDirectory("home"));
		_rootDirectory.changeOwner(root);
		_rootDirectory.getDirectory("home").changeOwner(root);
		
		_users.put(root.getUsername(), root);
	}

	/*
	*lookForLargest:
	*		@return: retorna a entrada que ocupa mais espaco em todo o sistema de ficheiros.
	*/
	public Entry lookForLargest()
	{
		Iterator iterator = _rootDirectory.iterator();
		Entry largest = _rootDirectory;
		while (iterator.hasNext())
		{
			Entry entry = (Entry) iterator.next();
			if (entry.getSize() > largest.getSize())
				largest = entry;
		}
		return largest;
	}
	
	/*
	*getRoot:
	*		@return a Root do sistema de ficheiros.
	*/
	public Root getRoot()
	{
		return (Root)_users.get("root");
	}

	/*
	*getRootDirectory:
	*		@return o Directory que vai representar a raiz do FileSystem (head).
	*/
	public Directory getRootDirectory()
	{
		return _rootDirectory;
	}

	/*
	*getUser:
	*		@param username: o id do user que queremos encontrar
	*		@exception UserUnknownException se o User nao existir.
	*		@return o User com o username pretendido.
	*/
	public User getUser(String username) throws UserUnknownException
	{
		if (_users.containsKey(username))
			return _users.get(username);

		else
			throw new UserUnknownException(username);
	}

	/*
	*userExists:
	*		@param username: id do user que queremos verificar se existe.
	*		@return: retorna true em caso de existencia false caso contrario.
	*/
	public Boolean userExists(String username)
	{
		return _users.containsKey(username);
	}

	/*
	*putNewUser:
	*		@param u: user que queremos acrescentar ao FileSystem
	*/
	public void putNewUser(User u)
	{	
		_users.put(u.getUsername(), u);
	}

	/*
	*users:
	*		@return o ArrayList com os utilizadores.
	*/
	public List<User> users()
	{
		return new ArrayList<User>(_users.values()); 
	}
}