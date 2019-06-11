package com.example.mymusic;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static String DB_PATH = "/data/data/com.sriyanksiddhartha.speechtotext/databases/";
    private static final String DATABASE_NAME = "dbsong";

    private static final String TABLE_SONG = "song";

    private static final String COLUMN_SONG_ID ="id";
    private static final String COLUMN_SONG_TITLE ="title";
    private static final String COLUMN_SONG_LYRIC = "lyric";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String script = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_SONG_ID + " INTEGER PRIMARY KEY," + COLUMN_SONG_TITLE + " TEXT,"
                + COLUMN_SONG_LYRIC + " TEXT" + ")";
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        onCreate(db);
    }

    public void createDefaultSong()  {
        int count = this.getNotesCount();
        if(count == 0 ) {
            Song song1 = new Song(1, "Một Bước Yêu Vạn Dặm Đau", "một thế giới hư ảo nhưng thật ấm áp em xuất hiện khiến những băng giá đời anh bỗng dần tan đi cuộc đời anh đặt tên là muộn phiền nên làm sao dám mơ mình may mắn được trọn vẹn cùng em ta phải xa em mặc kệ nước mắt em rơi vì những nguyên do cả đời không dám đối diện chỉ còn vài gang tấc nhưng lại xa xôi tình mình tựa đôi đũa lệch đành buông trôi cầu mong em sẽ sớm quên được tất cả tìm thấy một người xứng đáng ở bên từ nay duyên kiếp bỏ lại phía sau ngày và bóng tối chẳng còn khác nhau chẳng có nơi nào yên bình được như em bên anh  tình yêu một lần lỡ bước là muôn dặm trường đau thương hạt mưa bỗng hoá thành màu nỗi đau Trời như muốn khóc ngày mình mất nhau có bao nhiêu đôi ngôn tình cớ sao lìa xa mình ta".toLowerCase());
            Song song2 = new Song(2, "Nhỏ Ơi", "Lần đầu ta gặp nhỏ Trong nắng chiều bay bay Ngập ngừng ta hỏi nhỏ Nhỏ bảo nhỏ không tên Ừ thì nhỏ không tên Bây giờ quen nhé nhỏ Nhỏ ơi Lần này ta gặp nhỏ".toLowerCase() +
                    "Trong nắng chiều bay bay Ngập ngừng ta hỏi nhỏ Nhỏ bảo nhỏ chưa yêu Ừ thì nhỏ chưa yêu Bây giờ yêu nhé nhỏ Nhỏ ơi Lần này nhỏ quay đi Không thèm nhìn ta nữa Giọt sầu rơi một mình Chỉ còn ta một mình".toLowerCase() +
                    "Nhỏ ơi Còn gì đâu hỡi nhỏ Trong nắng chiều phôi phai Kỷ niệm ta cùng nhỏ Giờ chỉ là hư vô Ừ thì là hư vô Xa rồi vẫn nhớ hoài Nhỏ ơi Tình cờ ta gặp nhỏ Trong nắng vàng ban mai Thẹn thùng ta hỏi nhỏ".toLowerCase() +
                    "Nhỏ bảo khờ ghê đi Ừ thì khờ ghê đi Thương rồi sao chẳng hiểu Nhỏ ơi Lần này nhỏ quay đi Không thèm nhìn ta nữa Giọt sầu rơi một mình Chỉ còn ta một mình Nhỏ ơi Còn gì đâu hỡi nhỏ Trong nắng chiều phôi phai".toLowerCase() +
                    "Kỷ niệm ta cùng nhỏ Giờ chỉ là hư vô Ừ thì là hư vô Xa rồi vẫn nhớ hoài Nhỏ ơi Tình cờ ta gặp nhỏ Trong nắng vàng ban mai Thẹn thùng ta hỏi nhỏ Nhỏ bảo khờ ghê đi Ừ thì khờ ghê đi Thương rồi sao chẳng hiểu".toLowerCase() +
                    "Nhỏ ơi Ừ thì khờ ghê đi Thương rồi sao chẳng hiểu".toLowerCase());
            Song song3 =  new Song(3, "Trái Tim Bên Lề", "Em đã biết bao ngày bên em Là anh đã có bấy nhiêu ngày yêu em Nhưng em vẫn ngây thơ nên ko biết tình anh Thầm yêu em nên tim đau rã rời Anh đã thấy bao người yêu em".toLowerCase() +
                    "Tình anh đã nhức nhối thinh lặng bên em Nhìn em yêu tuy ngay đây nhưng rất xa xôi Vì tim em đã trót trao ai rồi Tình yêu sâu kín cho em đã bao ngày thầm lặng Nhìn em ko nói nên câu nói chi đây".toLowerCase() +
                    "Lòng xót xa đi bên đời em và thấy em chịu nhiều đắng cay mà tim anh đây vì yêu em nên đau rã rời Vì em đã trót yêu ai em quá vô tình ko nhận ra".toLowerCase() +
                    "Tình yêu anh đã trao em bấy lâu nay Một trái tim bên lề rất đau vì biết em chẳng hề biết đâu Tình yêu anh đã trao em rồi".toLowerCase());
            Song song4 = new Song(4, "Ngẫu Hứng Lý Qua Cầu", "Bằng lòng đi em về với quê anh Một cù lao xanh một dòng sông xanh Một vườn cây xanh hoa trái đơm hương Thuyền ai qua sông nụ cười mênh mông Bằng lòng đi em anh đón qua cầu".toLowerCase() +
                    "Mùa mưa cầu tre dẫu khó đưa dâu Bằng lòng bên anh dưới mái tranh nghèo Về đây người quê chỉ có tấm lòng Có chiếc xuồng ba lá để yêu em Ôi đóa hoa tím trôi liu riu Dòng sông nước chảy liu riu Anh thấy em nhỏ xíu nhỏ xíu anh thương".toLowerCase() +
                    "Ôi những đêm ngắm trăng Nhớ em buồn muốn khóc Mình anh ca điệu lý qua cầu Bằng lòng đi em về với quê anh Một cù lao xanh một dòng sông xanh Bằng lòng anh xin thưa má thưa ba Mình ngồi bên nhau chuyện trò đêm thâu".toLowerCase() +
                    "Mình ngồi bên nhau nghe tiếng chuông chùa Bình yên tựa nghe câu hát xa đưa Mình ngồi bên nhau dưới ánh sao mờ Lặng nghe dòng sông tiếng sóng vỗ bờ Tiếng đàn kìm man mác buồn trên sông".toLowerCase() +
                    "Ôi đóa hoa tím trôi liu riu Dòng sông nước chảy liu riu Anh thấy em nhỏ xíu nhỏ xíu anh thương Ôi những đêm ngắm sông Nhớ em buồn muốn khóc Mình anh ca điệu lý qua cầu".toLowerCase() +
                    "Bằng lòng đi em Bằng lòng đi em Bằng lòng đi dù muôn trùng xa Khúc dân ca bắt cầu em tớ Bằng lòng đi người yêu nhỏ bé Nhớ thương nhau điệu lý qua cầu".toLowerCase());
            this.addNote(song1);
            this.addNote(song2);
            this.addNote(song3);
            this.addNote(song4);
        }
    }

    public void addNote(Song song) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG_TITLE, song.getName_song());
        values.put(COLUMN_SONG_LYRIC, song.getLyrsic_song());

        db.insert(TABLE_SONG, null, values);

        db.close();
    }
    public int getNotesCount() {

        String countQuery = "SELECT  * FROM " + TABLE_SONG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }
    public Song getSong(String s){
        Song result = null;
        String key = s.toLowerCase();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_SONG + " where " + COLUMN_SONG_LYRIC + " like ?", new String[] { "%" + key + "%" });
        if (cursor.getCount() >=1 ) {
            cursor.moveToFirst();
            result = new Song(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        return result;
    }
}
