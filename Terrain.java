import java.util.Arrays;
import java.util.Random;

/**
 * Die Klasse Terrain repräsentiert ein Gelände mit verschiedenen Geländemerkmalen.
 */
class Terrain {
    private char[] terrain = new char[4];
    private String map = "";

    public Terrain(char[] terrain) {
        this.terrain = terrain;
    }

    public Terrain() {
        generate();
    }

    /**
     * Gibt das Gelände formatiert auf der Konsole aus.
     */
    public String print() {
        // Die Karte zeigt das Gelände mit speziellen Symbolen an
        // Das Symbol "b" steht für ein Hindernis im Gelände.
        // Die Zahlen "0", "1", "2" und "3" repräsentieren verschiedene Werte oder Eigenschaften des Geländes.
        // Das Symbol "x" steht für ein anderes Merkmal.
        // Das "/" steht für eine Grenze im Gelände.
        map =
                "d 0 0 0 a\n" +
                "3 d 0 a 1\n" +
                "3 3 x 1 1\n" +
                "3 c 2 b 1\n" +
                "c 2 2 2 b";

        // Ersetze die Symbole auf der Karte mit den entsprechenden Geländemerkmalen
        for (int i = 0; i < terrain.length; i++) {
            map = map.replace((char) (i + '0'), terrain[i]);
            int next = i == terrain.length - 1 ? 0 : i + 1;
            char border = terrain[i];
            if (terrain[next] != terrain[i]) {
                border = i % 2 == 0 ? '/' : '\\';
            }
            map = map.replace((char) (i + 'a'), border);
        }// Gib die formatierte Karte aus
        return map;
    }

    /**
     * Generiert das Gelände, indem zufällig Geländemerkmale ausgewählt werden.
     */
    public char[] generate() {
        char[] type = {'*', '-', 'o', '-'};

        Random random = new Random();
        for (int i = 0; i < terrain.length; i++) {
            terrain[i] = type[random.nextInt(type.length)];
        }

        return terrain;
    }

    public char[] getTerrain() {
        return terrain;
    }

    /**
     * Gibt das Geländemerkmal im Norden zurück.
     *
     * @return Das Geländemerkmal im Norden.
     */
    public char getNorth() {
        return terrain[0];
    }

    /**
     * Gibt das Geländemerkmal im Süden zurück.
     *
     * @return Das Geländemerkmal im Süden.
     */
    public char getSouth() {
        return terrain[2];
    }

    /**
     * Gibt das Geländemerkmal im Osten zurück.
     *
     * @return Das Geländemerkmal im Osten.
     */
    public char getEast() {
        return terrain[1];
    }

    /**
     * Gibt das Geländemerkmal im Westen zurück.
     *
     * @return Das Geländemerkmal im Westen.
     */
    public char getWest() {
        return terrain[3];
    }

    /**
     * Gibt eine Zeile des Geländes als String zurück.
     *
     * @param i Die Indexnummer der Zeile (1-5).
     */
    public String getRow(int i) {
        String row = "";
        String nmap = map.replace(" ", "").replace("\n", "");
        row = nmap.substring((i - 1) * 5, (i - 1) * 5 + 5);
        row = row.replace("", " ").stripTrailing();

        return row;
    }

    /**
     * Gibt eine textuelle Repräsentation des Geländes aus.
     *
     * @return Eine textuelle Repräsentation des Geländes.
     */
    public String toString() {
        System.out.println("Terrain used => " + Arrays.toString(terrain));
        System.out.println("East => " + getEast() + " North => " + getNorth() + " South => " + getSouth() + " West => " + getWest());
        return null;
    }

    public void init(){
        generate();
        utils.p(print());
    }
}
