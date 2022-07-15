package models;

public interface IRoom {
    String getRoomNumber();

    Double getRoomPrice();

    Room.RoomType getRoomType();

    boolean isFree();
}
