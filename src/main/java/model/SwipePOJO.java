package model;

public class SwipePOJO {
    private int id;
    private int swipee;
    private int swiper;

    public SwipePOJO(int id, int swipee, int swiper) {
        this.id = id;
        this.swipee = swipee;
        this.swiper = swiper;
    }

    public SwipePOJO(int swipee, int swiper) {
        this.swipee = swipee;
        this.swiper = swiper;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSwipee() {
        return swipee;
    }

    public void setSwipee(int swipee) {
        this.swipee = swipee;
    }

    public int getSwiper() {
        return swiper;
    }

    public void setSwiper(int swiper) {
        this.swiper = swiper;
    }

    @Override
    public String toString() {
        return "SwipePOJO{" +
                "id=" + id +
                ", swipee=" + swipee +
                ", swiper=" + swiper +
                '}';
    }
}
