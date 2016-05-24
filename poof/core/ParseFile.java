package poof.core;

import poof.textui.exception.*;
import poof.core.*;

import java.io.*;
import pt.utl.ist.po.ui.InvalidOperation;


import java.util.*;
/**
 * É necessário preencher os métodos createEntry e createUser de acordo com a interface
 * e funcionalidade das entidades do domínio da aplicação concretizadas por cada grupo.
 *
 * Os alunos podem ter utilizados outros nomes para representar as entidades Sistema de Ficheiros,
 * directório (Directory), ficheiro (File) e a super classe comum a directório e ficheiro (Entry).
 * Se for esse o caso, então é necessário substituir neste ficheiro estes nomes pelos nomes utilizados
 * por cada grupo.
 **/

public class ParseFile {

  private ActualState _newAs; 

  public FileSystem parse(String fileName) throws InvalidOperation
  {
    try
    {
      BufferedReader reader = new BufferedReader(new FileReader(fileName));

      _newAs = new ActualState();

      String line;

      while ((line = reader.readLine()) != null) {
        parseLine(line);
      }
    }
    catch (IOException ex)
    { 
      ex.printStackTrace();
    }
    return _newAs.getFileSystem();
  }

  public void parseLine(String line) throws InvalidOperation 
  {
    String[] args = line.split("\\|");
    
    switch (args[0]) {
    case "USER":
      createUser(args[1], args[2], args[3]);
      break;
    case "DIRECTORY":
      createDirectory(args[1], args[2], args[3]);
      break;
    case "FILE":
      createFile(args[1], args[2], args[3], args[4]);
      break;
    }
  }
  
  public void createUser(String username, String name, String homeDir) throws InvalidOperation 
  { 
    _newAs.getFileSystem().getRoot().createNewUser(_newAs.getFileSystem(), name, username);
    User u = _newAs.getFileSystem().getUser(username);
    _newAs.changeActualUser(u);
    _newAs.changeActualDirectory(u.getHomeDirectory());
  }

  private Entry createEntry(String path, String username, String permission, boolean isDir) throws InvalidOperation
  {
    int last = path.lastIndexOf('/');
    String parentPath = path.substring(0, last);
    String entryName = path.substring(last + 1);
    
    User u = _newAs.getFileSystem().getUser(username);

    String[] splitParentPath = parentPath.split("\\/");
    int size = splitParentPath.length;

    _newAs.changeActualDirectory(_newAs.getFileSystem().getRootDirectory());

    for(int i = 1; i < size; i++)
      {
        if (!_newAs.getActualDirectory().directoryExists(splitParentPath[i]))
        {
          _newAs.getActualDirectory().createSubDirectory(splitParentPath[i], _newAs.getActualDirectory().getOwner());
          _newAs.changeActualDirectory(_newAs.getActualDirectory().getDirectory(splitParentPath[i])); 
        }
        else
          _newAs.changeActualDirectory(_newAs.getActualDirectory().getDirectory(splitParentPath[i]));
      }

    if (isDir){
      Directory entry;
      _newAs.getActualDirectory().createSubDirectory(entryName, u);
      _newAs.changeActualDirectory(_newAs.getActualDirectory().getDirectory(entryName));


      if(permission.equals("private"))
        _newAs.getActualDirectory().changePermission(false);  

      else
        _newAs.getActualDirectory().changePermission(true);

      _newAs.changeActualUser(u);
      return _newAs.getActualDirectory();
      }

    else{
      File entry;
      _newAs.getActualDirectory().createFile(entryName, u);

      if(permission.equals("private"))
        _newAs.getActualDirectory().getFile(entryName).changePermission(false);

      else
        _newAs.getActualDirectory().getFile(entryName).changePermission(true);


      _newAs.getActualDirectory().getFile(entryName).changeOwner(u);
      return _newAs.getActualDirectory().getFile(entryName);
    }
  }

  public void createFile(String path, String username, String permission, String content) throws InvalidOperation
  {
    poof.core.File file = (poof.core.File)createEntry(path, username, permission, false);
    file.addContent(content);
  }

    public void createDirectory(String path, String username, String permission) throws InvalidOperation
    {
      createEntry(path, username, permission, true);
    }   

}
