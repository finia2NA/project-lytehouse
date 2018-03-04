package net.lighthouse.levels;

import acm.util.RandomGenerator;
import net.lighthouse.model.BBlock;
import net.lighthouse.util.BlockList;

import java.awt.Color;

/**
 * A level is maximal 5 rows and 7 columns. If a block should be left empty set his color to null.
 *
 * @author Christoph Fricke
 */
public final class LevelManager {
    private static Color[] level0 = {
        Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN,
        Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW,
        Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN,
        Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW,
        Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN,
    };

    private static Color[] level1 = {
        Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN,
        null, null, null, null, null, null, null,
        Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW,
        null, null, null, null, null, null, null,
        Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN, Color.YELLOW, Color.CYAN,
    };

    private static Color[] level2 = {
        Color.GREEN, null, Color.GREEN, null, Color.GREEN, null, Color.GREEN,
        null, Color.CYAN, null, Color.CYAN, null, Color.CYAN, null,
        Color.GREEN, null, Color.GREEN, null, Color.GREEN, null, Color.GREEN,
        null, Color.CYAN, null, Color.CYAN, null, Color.CYAN, null,
        Color.GREEN, null, Color.GREEN, null, Color.GREEN, null, Color.GREEN,
    };

    public static BlockList getRandomLevel() {
        RandomGenerator rnd = RandomGenerator.getInstance();
        switch (rnd.nextInt(3)) {
            case 0:
                return makeBlockList(level0);
            case 1:
                return makeBlockList(level1);
            case 2:
                return makeBlockList(level2);
            default:
                return makeBlockList(level0);
        }
    }

    private static BlockList makeBlockList(Color[] level) {
        BlockList list = new BlockList();

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 7; x++) {
                if (level[(y * 7) + x] != null) {
                    BBlock block = new BBlock(x * 80, y * 60, 80, 60, level[(y * 7) + x]);
                    list.add(block);
                }
            }
        }

        return list;
    }
}
