package com.example.mymusic;

public class Song {
    private int id;
    private String name_song;
    private String lyrsic_song;
    private String tacgia = null;


    public Song(int anInt, String name, String lyrsic_song) {
        this.id = anInt;
        this.name_song = name;
        this.lyrsic_song = lyrsic_song;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_song() {
        return name_song;
    }

    public void setName_song(String name_song) {
        this.name_song = name_song;
    }

    public String getLyrsic_song() {
        return lyrsic_song;
    }

    public void setLyrsic_song(String lyrsic_song) {
        this.lyrsic_song = lyrsic_song;
    }
}
