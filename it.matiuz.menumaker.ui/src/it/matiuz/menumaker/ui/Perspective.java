package it.matiuz.menumaker.ui;

import it.matiuz.menumaker.ui.views.CategoryView;
import it.matiuz.menumaker.ui.views.ItemView;
import it.matiuz.menumaker.ui.views.MenuItemView;
import it.matiuz.menumaker.ui.views.MenuView;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory
{

  @Override
  public void createInitialLayout (IPageLayout layout)
  {
    final String editorArea = layout.getEditorArea ();
    layout.setEditorAreaVisible (false);

    addFastViews (layout);
    layout.addView (CategoryView.ID, IPageLayout.LEFT, 0.45f, editorArea);
    layout.addView (ItemView.ID, IPageLayout.BOTTOM, 0.5f, CategoryView.ID);
    layout.addView (MenuItemView.ID, IPageLayout.LEFT, 0.5f, editorArea);
    layout.addView (MenuView.ID, IPageLayout.BOTTOM, 0.7f, MenuItemView.ID);
  }

  private void addFastViews (IPageLayout layout)
  {
    System.currentTimeMillis ();
  }
}
