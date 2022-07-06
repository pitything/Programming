
public class TestHash implements Comparable{
    private int x;
    private int y;
    private boolean sortType;

    TestHash(int x, int y, boolean sortType){
        this.x = x;
        this.y = y;
        this.sortType = sortType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestHash testHash = (TestHash) o;

        if (x != testHash.x) return false;
        if (y != testHash.y) return false;
        return sortType == testHash.sortType;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public int compareTo(Object o) {
        if(this == null) return -1;
        if(o == null || !(o instanceof TestHash)) return -1;
        return this.sortType ? (this.x > ((TestHash)o).x ? 1 : ((this.x == ((TestHash)o).x) ? 0 : -1))
                : (this.x < ((TestHash)o).x ? 1 : ((this.x == ((TestHash)o).x) ? 0 : -1));
    }

    @Override
    public String toString() {
        return "TestHash{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
