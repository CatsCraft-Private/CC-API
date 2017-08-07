/*
 * Copyright (c) created class file on: 2016.
 * All rights reserved.
 * Copyright owner: brainsynder/Magnus498
 * To contact the developer go to:
 * - spigotmc.org and look up brainsynder
 * - email at: briansnyder498@gmail.com
 * - or Skype at live:starwars4393
 */

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package api.brainsynder.Utils;

import java.util.Arrays;
import java.util.List;

public class RainbowWords {
    private static final List<String> RAINBOW = Arrays.asList("§4", "§c", "§6", "§e", "§a", "§2", "§b", "§3", "§9", "§1", "§5", "§d");
    private int place = 0;
    private String text = "You did not provide any text.";
    private String fancyText = "§4You did not provide any text";
    private List<String> rainbowArray = null;
    private String prefix = "";

    public RainbowWords(String text) {
        this.place = 0;
        if (text != null) {
            this.text = text;
        }

        if (this.rainbowArray == null) {
            this.rainbowArray = RAINBOW;
        }

        this.updateFancy();
    }

    public RainbowWords(String text, String formatCode) {
        this.place = 0;
        if (text != null) {
            this.text = text;
        }

        if (formatCode != null) {
            this.prefix = formatCode;
        }

        if (this.rainbowArray == null) {
            this.rainbowArray = RAINBOW;
        }

        this.updateFancy();
    }

    public RainbowWords(String text, List<String> rainbowArray) {
        this.place = 0;
        if (text != null) {
            this.text = text;
        }

        if (this.rainbowArray == null) {
            this.rainbowArray = rainbowArray;
        }

        this.updateFancy();
    }

    public static List<String> getDefaultRainbow() {
        return RAINBOW;
    }

    private void updateFancy() {
        int spot = this.place;
        StringBuilder fancyText = new StringBuilder();
        for (String letter : this.text.split(" ")) {
            if (letter.equals(text.split(" ")[0])) {
                fancyText.append(this.rainbowArray.get(spot)).append(letter);
            } else {
                fancyText.append(' ').append(this.rainbowArray.get(spot)).append(letter);
            }
            if (spot == this.rainbowArray.size() - 1) {
                spot = 0;
            } else {
                ++spot;
            }
        }

        this.fancyText = fancyText.toString();
    }

    public void moveRainbow() {
        if (this.rainbowArray.size() - 1 == this.place) {
            this.place = 0;
        } else {
            ++this.place;
        }

        this.updateFancy();
    }

    public void moveRainbowRight() {
        if (this.place == 0) {
            this.place = this.rainbowArray.size() - 1;
        } else {
            --this.place;
        }

        this.updateFancy();
    }

    public String getOrigonalText() {
        return this.text;
    }

    public String getText() {
        return this.fancyText;
    }

    public int getPlace() {
        return this.place;
    }

    public void setPlace(int place) {
        if (place <= RAINBOW.size() - 1 && place >= 0) {
            this.place = place;
            this.updateFancy();
        }
    }

    public List<String> getRainbow() {
        return this.rainbowArray;
    }

    public String getFormatPrefix() {
        return this.prefix;
    }

    public void setFormatPrefix(String prefix) {
        this.prefix = prefix;
    }
}
