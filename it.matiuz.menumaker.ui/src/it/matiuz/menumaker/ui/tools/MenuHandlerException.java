package it.matiuz.menumaker.ui.tools;

public class MenuHandlerException extends Exception
{
  private static final long serialVersionUID = 1L;

  private String message;

  public MenuHandlerException (String uMessage)
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
