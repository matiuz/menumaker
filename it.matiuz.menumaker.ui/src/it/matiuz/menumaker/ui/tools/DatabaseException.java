package it.matiuz.menumaker.ui.tools;

public class DatabaseException extends Exception
{
  private static final long serialVersionUID = 4252259820960505020L;

  private String message;

  public DatabaseException (String uMessage)
  {
    message = uMessage;
  }

  @Override
  public String getMessage ()
  {
    return message;
  }

  public void setMessage (String uMessage)
  {
    message = uMessage;
  }
}
