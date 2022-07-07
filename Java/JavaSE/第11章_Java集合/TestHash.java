
public class TestHash implements Comparable{
    private int x;
    private boolean sortType;

    TestHash(int x, boolean sortType){
        this.x = x;
        this.sortType = sortType;
    }

    @Override
    public int compareTo(Object o) {
        if(this == null) return -1;
        if(o == null || !(o instanceof TestHash)) return -1;
//        return this.sortType ? (this.x > ((TestHash)o).x ? 1 : ((this.x == ((TestHash)o).x) ? 0 : -1))
//                : (this.x < ((TestHash)o).x ? 1 : ((this.x == ((TestHash)o).x) ? 0 : -1));
        return (this.x > ((TestHash)o).x ? 1 : ((this.x == ((TestHash)o).x) ? 0 : -1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestHash testHash = (TestHash) o;

        if (x != testHash.x) return false;
        return sortType == testHash.sortType;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result;
        return result;
    }

    @Override
    public String toString() {
        return "TestHash{" +
                "x=" + x +
                '}';
    }
}
