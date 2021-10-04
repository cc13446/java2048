public class Tile {

    private int id;

    public Tile(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public void moveTo(Tile tile) {
        if (this.id > 0 && tile.getId() == 0) {
            int temp = this.id;
            this.id = tile.getId();
            tile.setId(temp);
        }
    }
    public long merge(Tile tile) {
        long res = 0;
        if (this.id == tile.getId()) {
            res = this.id;
            this.id *= 2;
            tile.setId(0);
        }
        return res;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
