package poof.core;
import java.util.*;
import poof.textui.exception.EntryExistsException;
import poof.textui.exception.EntryUnknownException;
import poof.textui.exception.IsNotDirectoryException;
import java.io.*;
/*
*A class ActualState representa o estado actual de utilizacao de um FileSystem e vai conter o directorio actual em que estamos
*	a trabalhar, o FileSystem correspondente e o User activo. Esta class vai permitir a realizacao de operacoes sobre o FileSystem
*	e vai permitir, se necessario, numa futura utilizacao/extencao do programa ter varios estados de utilizacao sobre o mesmo 
*	FileSystem (ex; diferentes Shells).
*/
public class ActualState implements java.io.Serializable
{
	//User que reprensenta o User actual.
	private User _actualUser;
	//Directorio que representa o directorio actual.
	private Directory _actualDirectory;
	//FileSystem com que estamos a trabalhar.
	private FileSystem _actualFileSystem;

	/*
	*construtor:
	*		vai criar um FileSystem que vai ser o FileSystem que vamos utilizar,
	*		o User que vai estar logado e a root,
	*		e o Directorio sobre o qual vamos estar a trabalhar e o directorio da root.
	*/
	public ActualState() throws EntryExistsException, EntryUnknownException, IsNotDirectoryException
	{
		_actualFileSystem = new FileSystem();
		_actualUser = _actualFileSystem.getRoot();
		_actualDirectory = _actualUser.getHomeDirectory();
	}

	/*
	*getFileSystem:
	*		@return: vai retornar o FileSystem sobre o qual estamos a trabalhar.
	*/
	public FileSystem getFileSystem()
	{
		return _actualFileSystem;
	}

	/*
	*getActualDirectory:
	*		@return: vai retornar o Directorio sobre o qual estamos a trabalhar.
	*/
	public Directory getActualDirectory()
	{
		return _actualDirectory;
	}
	/*
	*getActualUser:
	*		@return: vai retornar o User que esta logado.
	*/
	public User getActualUser()
	{
		return _actualUser;
	}

	/*
	*changeActualDirectory:
	*		@param: newDirectory vai ser o directorio que vamos querer que seja o directorio actual.
	*/
	public void changeActualDirectory(Directory newDirectory)
	{
		_actualDirectory = newDirectory;
	}

	/*
	*changeActualUser:
	*		@param: newUser vai ser o novo User respondavel pelas operacoes que vamos realizar no FileSystem.
	*/
	public void changeActualUser(User newUser)
	{
		_actualUser = newUser;
	}
	/*
	*changeActualFileSystem:
	*		@param: newFilesystem vai ser o novo FileSystem sobre o qual vamos realizar operacoes.
	*/
	public void changeActualFileSystem(FileSystem newFilesystem)
	{
		_actualFileSystem = newFilesystem;
	}
	
	/*
	*saveStatus:
	*		@param: filename vai ser o nome do ficheiro onde vamos guardar este estado.
	*/
	public void saveStatus(String filename)
    {
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)));

            out.writeObject(this);
            out.flush();
            out.close();
        }

        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /*
    *loadStatus:
    *		@param: filename e o nome do ficheiro que vamos querer abrir.
    */
    public void loadStatus(String filename)
    {
    	ActualState as;

        try
        {
            ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)));

            as = (ActualState)in.readObject();
            this.changeActualUser(as.getActualUser());
            this.changeActualDirectory(as.getActualUser().getHomeDirectory());
            this.changeActualFileSystem(as.getFileSystem());
        }

        catch (ClassNotFoundException|IOException ex) 
        { 
            ex.printStackTrace();
        }
    }

}