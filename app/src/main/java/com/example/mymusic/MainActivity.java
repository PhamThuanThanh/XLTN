package com.example.mymusic;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView txvResult;
    ImageButton btnSpeak;
    DBHelper db;
    private Dialog dialog;
    private AlertDialog alerdialog;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        db.createDefaultSong();
        txvResult = (TextView) findViewById(R.id.txvResult);
        btnSpeak = (ImageButton) this.findViewById(R.id.btnSpeak);
        try {
            mDb = db.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
        checkPermission();

        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                ArrayList<String> matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null)
                    txvResult.setText(matches.get(0));
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });
        findViewById(R.id.btnSpeak).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP: // thả tay để ngừng và nhận kết quả
                        mSpeechRecognizer.stopListening();

                        break;

                    case MotionEvent.ACTION_DOWN:   // giữ button để hát
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        break;
                }
                return false;
            }
        });

        txvResult.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = txvResult.getText().toString().toLowerCase();
                dialog = new Dialog(MainActivity.this);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                Song song = db.getSong(s);

                if (song == null){
                    Toast.makeText(MainActivity.this, "Không tìm thấy bài hát! Cần sự trợ giúp từ Google",
                            Toast.LENGTH_LONG).show();
                    builder1.setTitle("Không tìm thấy bài hát! \n");
                    builder1.setMessage("Bạn có muốn tìm kiếm trên Google không?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Uri uri = Uri.parse("https://www.google.com/search?q=" + txvResult.getText().toString());
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }


                else {
                    final String name = song.getName_song();
//					Toast.makeText(MainActivity.this, song.getName_song(), Toast.LENGTH_LONG).show();
//					dialog.setTitle("1234");
//					dialog.setContentView(R.layout.dialog);
//					dialog.show();
                    builder1.setTitle("Bài hát bạn muốn tìm kiếm: \n"   + song.getName_song());
                    builder1.setMessage("Bạn có muốn nghe thử?");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Uri uri = Uri.parse("https://www.youtube.com/results?search_query=" + name);
                                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                    startActivity(intent);
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            }
        });
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }
}
