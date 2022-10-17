package com.example.volleygsonjsonekg.placeholder;

import com.example.volleygsonjsonekg.GameCompanyModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    public static final List<GameCompanyModel> ITEMS = new ArrayList<GameCompanyModel>();

    /**
     * A map of sample (placeholder) items, by ID.
     */
    public static final Map<String, GameCompanyModel> ITEM_MAP = new HashMap<String, GameCompanyModel>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createGameCompanyModel(i));
        }
    }

    private static void addItem(GameCompanyModel item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getName(), item);
    }

    private static GameCompanyModel createGameCompanyModel(int position) {
        return new GameCompanyModel(String.valueOf(position), position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
}