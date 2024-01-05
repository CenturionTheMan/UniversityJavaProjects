package lab4_code.zad1;

import javax.swing.ListCellRenderer;

import java.awt.Component;

import javax.swing.JList;

public class CustomJListRenderer implements ListCellRenderer<JListItem> {

    int width, height;

    public CustomJListRenderer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends JListItem> list, JListItem value, int index, boolean isSelected, boolean cellHasFocus) {
        
        value.SetSize(width, height);

        value.ChangeIsSelected(isSelected);

        return value;
    }
}
