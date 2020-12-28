package id.ac.its.aff231yz160zlp118;

public enum ButtonClicked {
    PLAY(1),
    CREDITS(2),
    MAIN_MENU(3),
    HIGH_SCORE(4),
    LEVEL_0(10),
    REFERENCE(99),
    NOT_CLICKED(0);

    private final int value;

    ButtonClicked(int value) {
        this.value = value;
    }
}
