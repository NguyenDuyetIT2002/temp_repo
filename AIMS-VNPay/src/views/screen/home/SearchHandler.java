package views.screen.home;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class SearchHandler {
    public static List<MediaHandler> handleSearch(List<MediaHandler> homeItems, String searchText) {

        List<MediaHandler> searchItems = new ArrayList<>();
        homeItems.forEach(mediaHandler -> {
            if (mediaHandler.getMedia().getTitle().toLowerCase().contains(searchText.toLowerCase())) {
                searchItems.add(mediaHandler);
            }
        });
        return searchItems;
    }

}
