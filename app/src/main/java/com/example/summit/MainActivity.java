package com.example.summit;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.summit.data.Config;
import com.example.summit.data.Req;
import com.example.summit.data.Res;
import com.example.summit.data.ServiceApi;
import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.longrunning.OperationFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.LongRunningRecognizeMetadata;
import com.google.cloud.speech.v1.LongRunningRecognizeRequest;
import com.google.cloud.speech.v1.LongRunningRecognizeResponse;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.speech.v1.SpeechSettings;
import com.google.protobuf.ByteString;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private Button startButton;
    private Button summ;
    private TextView resultTextView;
    private ProgressBar progressBar;
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};
    private String data = "";
    private Thread recordingThread;
    private byte[] byteArray;
    private VoiceRecorder mVoiceRecorder;
    Handler handler = new Handler();
    private MediaPlayer mediaPlayer;

    private final VoiceRecorder.Callback mVoiceCallback = new VoiceRecorder.Callback() {

        @Override
        public void onVoiceStart() {

        }

        @Override
        public void onVoice(byte[] data, int size) {

            byteArray = appendByteArrays(byteArray, data);

        }


        @Override
        public void onVoiceEnd() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.VISIBLE);

                }
            });
            Log.e("kya",""+byteArray);
            transcribeRecording(byteArray);
        }
    };
    private SpeechClient speechClient;

    Config configApi = new Config();
    ServiceApi Sevice = configApi.getApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.start_button);
        summ = findViewById(R.id.summ);
        resultTextView = findViewById(R.id.result_text_view);
        progressBar = findViewById(R.id.progress_bar);
        ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (permissionToRecordAccepted) {

                    if (startButton.getText().toString().equals("Start")) {
                        startButton.setText("Stop");
                        startVoiceRecorder();
                    } else {
                        stopVoiceRecorder();
                    }
                }

            }
        });

        summ.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Req payload = new Req(data);
                Call<List<Res>> call = Sevice.query(payload);
                if (Objects.equals(data, "") ||data==null){
                    Toast.makeText(MainActivity.this, "data masih Kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                call.enqueue(new Callback<List<Res>>() {
                    private static final String TAG = "enq";

                    @Override
                    public void onResponse(Call<List<Res>> call, Response<List<Res>> response) {
                        try {
                            if (response.isSuccessful()) {
                                List<Res> res = response.body();
                                if (res != null) {
                                    final Res ress = res.get(0);
                                    String summaryText = ress.getSummaryText();
                                    Log.i("response", summaryText);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            resultTextView.setText(summaryText);
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    });

                                    // Use summaryText within the Res object as needed
                                }

                            } else {
                                Toast.makeText(MainActivity.this, "Gagal Menyambung Ulangi lagi!", Toast.LENGTH_SHORT).show();
                                System.out.println("Unsuccessful response");
                                Log.i("adsadasd", "ooijjuj");
                            }
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this,  e.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("Exception", "Error: " + e.getMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Res>> call, Throwable t) {
                        Log.e("adsadasd", t.toString());
                    }
                });
            }
        });


        initializeSpeechClient();
    }

    private void initializeSpeechClient() {
        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream(getResources().openRawResource(R.raw.credentials));
            FixedCredentialsProvider credentialsProvider = FixedCredentialsProvider.create(credentials);
            speechClient = SpeechClient.create(SpeechSettings.newBuilder().setCredentialsProvider(credentialsProvider).build());
        } catch (IOException e) {
            Toast.makeText(MainActivity.this,  e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("kya", "InitException" + e.getMessage());
        }
    }

    private void transcribeRecording(byte[] data) {
        try {
            progressBar.setVisibility(View.VISIBLE);
            Log.e("API_CALL", "API CALL STARTED...");

            recordingThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response =
                                speechClient.longRunningRecognizeAsync(createRecognizeRequestFromVoice(data));

                        LongRunningRecognizeResponse longRunningResponse = response.get();
                        List<SpeechRecognitionResult> results = longRunningResponse.getResultsList();
                        for (SpeechRecognitionResult result : results) {
                            String transcript = result.getAlternativesList().get(0).getTranscript();
                            updateResult(transcript);
                        }
                    } catch (Exception e) {
                        Log.e("SEECOLE", "" + e.getMessage());
                        Toast.makeText(MainActivity.this, "Pesan Anda di sini", Toast.LENGTH_SHORT).show(); }
                }
            });
            recordingThread.start();
        } catch (Exception e) {
            Log.e("SEECOLE", "" + e.getMessage());
        }
    }

    private LongRunningRecognizeRequest createRecognizeRequestFromVoice(byte[] audioData) {
        RecognitionAudio audioBytes = RecognitionAudio.newBuilder().setContent(ByteString.copyFrom(audioData)).build();
        RecognitionConfig config = RecognitionConfig.newBuilder()
                .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                .setSampleRateHertz(16000)
                .setLanguageCode("id")
                .build();
        return LongRunningRecognizeRequest.newBuilder()
                .setConfig(config)
                .setAudio(audioBytes)
                .build();
    }

    //    Req payload =new Req("The tower is 324 metres (1,063 ft) tall, about the same height as an 81-storey building, and the tallest structure in Paris. Its base is square, measuring 125 metres (410 ft) on each side. During its construction, the Eiffel Tower surpassed the Washington Monument to become the tallest man-made structure in the world, a title it held for 41 years until the Chrysler Building in New York City was finished in 1930. It was the first structure to reach a height of 300 metres. Due to the addition of a broadcasting aerial at the top of the tower in 1957, it is now taller than the Chrysler Building by 5.2 metres (17 ft). Excluding transmitters, the Eiffel Tower is the second tallest free-standing structure in France after the Millau Viaduct.");
    private void updateResult(final String transcript) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                playSound();
                progressBar.setVisibility(View.GONE);
                data = transcript;
                Log.d(TAG, "run: transcript : " + transcript);
                resultTextView.setText(transcript);
                clearByteArray(byteArray);
                startButton.setText("Start");
                stopVoiceRecorder();

            }
        });
    }

    private void startVoiceRecorder() {

        if (mVoiceRecorder != null) {
            mVoiceRecorder.stop();
        }
        mVoiceRecorder = new VoiceRecorder(mVoiceCallback);
        mVoiceRecorder.start();
    }

    private void stopVoiceRecorder() {
        if (mVoiceRecorder != null) {
            mVoiceRecorder.stop();
            mVoiceRecorder = null;
        }

        if (recordingThread != null) {
            try {
                recordingThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            recordingThread = null;
        }

    }

    private byte[] appendByteArrays(byte[] array1, byte[] array2) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            outputStream.write(array1);
            outputStream.write(array2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputStream.toByteArray();
    }

    private void clearByteArray(byte[] array) {
        // Set each element to zero
        Arrays.fill(array, (byte) 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
        if (!permissionToRecordAccepted) {
            startButton.setEnabled(false);
        }
    }

    private void playSound() {
        mediaPlayer = MediaPlayer.create(this, R.raw.transcribe_voice);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
            }
        });
        mediaPlayer.start();
    }
}