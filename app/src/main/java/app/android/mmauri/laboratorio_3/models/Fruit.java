package app.android.mmauri.laboratorio_3.models;

/**
 * Created by marc on 10/16/17.
 */

public class Fruit {
    String name;
    String description;
    int iconImg;
    int backgroundImg;
    int quant;

    public static final int LIMIT_QUANTITY = 10;
    public static final int RESET_VALUE_QUANTITY = 0;

    Fruit() {
    }

    public Fruit(String name, String description, int iconImg, int backgroundImg, int quantity) {
        this.name = name;
        this.description = description;
        this.iconImg = iconImg;
        this.backgroundImg = backgroundImg;
        this.quant = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIconImg() {
        return iconImg;
    }

    public void setIconImg(int iconImg) {
        this.iconImg = iconImg;
    }

    public int getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(int backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
            this.quant = quant;
    }

    /* Incrementar en 1 la cantidad */
    public void increaseQuant() {
        if (this.quant < LIMIT_QUANTITY)
            ++this.quant;
    }

    /* Resetear la cantidad */
    public void restoreQuant() {
        this.quant = RESET_VALUE_QUANTITY;
    }
}
