package com.zhg.api.samples.design;

/**
 * Created by nyzhang on 2015/12/30.
 */
public class MenuItem {
    boolean isSelected;
    String text;
    int icon;
    int iconSelected;

    public MenuItem(boolean isSelected, String text, int icon, int iconSelected) {
        this.isSelected = isSelected;
        this.text = text;
        this.icon = icon;
        this.iconSelected = iconSelected;
    }
}
