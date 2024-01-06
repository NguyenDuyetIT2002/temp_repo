package views.screen.home;

import javafx.scene.control.MenuButton;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortHandler {
    public static List<MediaHandler> sortByAscendingPrice(List<MediaHandler> homeItems) {
        List<MediaHandler> sortedItems = new ArrayList<>(homeItems);
        Collections.sort(sortedItems, Comparator.comparingInt(o -> o.getMedia().getPrice()));
        return sortedItems;
    }

    public static List<MediaHandler> sortByDescendingPrice(List<MediaHandler> homeItems) {
        List<MediaHandler> sortedItems = new ArrayList<>(homeItems);
        Collections.sort(sortedItems, Comparator.comparingInt((MediaHandler o) -> o.getMedia().getPrice()).reversed());
        return sortedItems;
    }
}
