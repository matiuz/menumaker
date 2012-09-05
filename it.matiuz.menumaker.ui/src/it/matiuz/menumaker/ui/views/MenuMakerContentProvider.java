package it.matiuz.menumaker.ui.views;

import java.util.ArrayList;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public abstract class MenuMakerContentProvider implements IStructuredContentProvider
{
  private final ArrayList<Object> elementList;

  public MenuMakerContentProvider ()
  {
    elementList = new ArrayList<Object> ();
  }

  public void reload ()
  {
    elementList.clear ();
    final Object[] elements = fetchElements ();
    for (int i = 0; i < elements.length; i++)
      elementList.add (elements[i]);
  }

  @Override
  public Object[] getElements (Object uInputElement)
  {
    return elementList.toArray (new Object[elementList.size ()]);
  }

  @Override
  public void dispose ()
  {
  }

  @Override
  public void inputChanged (Viewer uViewer, Object uOldInput, Object uNewInput)
  {
  }

  public abstract Object[] fetchElements ();
}
