package logic;

import logic.Sprite;

import java.util.*;

/**
 * Sprite manager is responsible for holding all sprite objects, and cleaning up
 * sprite objects to be removed. All collections are used by the JavaFX
 * application thread. During each cycle (animation frame) sprite management
 * occurs. This assists the user of the API to not have to create lists to
 * later be garbage collected. Should provide some performance gain.
 * @author cdea
 */
public class SpriteManager {
    /**
     * All the sprite objects currently in play
     */
    private final static List<Sprite> GAME_ACTORS = new ArrayList<>();

    /** */
    public List<Sprite> getAllSprites() {
        return GAME_ACTORS;
    }

    /**
     * VarArgs of sprite objects to be added to the game.
     *
     * @param sprites
     */
    public void addSprites(Sprite... sprites) {
        GAME_ACTORS.addAll(Arrays.asList(sprites));
    }

    /**
     * VarArgs of sprite objects to be removed from the game.
     *
     * @param sprites
     */
    public void removeSprites(Sprite... sprites) {
        GAME_ACTORS.removeAll(Arrays.asList(sprites));
    }
}