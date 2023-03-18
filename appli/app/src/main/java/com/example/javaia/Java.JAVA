public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button recordButton = findViewById(R.id.record_button);

        recordButton.setOnClickListener(new View.OnClickListener() {
            private AudioRecorder recorder;
            private boolean isRecording = false;
            private Handler handler = new Handler();
            private Runnable stopRecording = new Runnable() {
                @Override
                public void run() {
                    stopRecording();
                }
            };

            @Override
            public void onClick(View v) {
                if (!isRecording) {
                    startRecording();
                    handler.postDelayed(stopRecording, 20000); // enregistrement de 20 secondes
                    recordButton.setText("Stop");
                } else {
                    stopRecording();
                    recordButton.setText("Enregistrer");
                }

                isRecording = !isRecording;
            }

            private void startRecording() {
                String fileName = "enregistrement.3gp"; // nom du fichier
                recorder = new AudioRecorder(fileName);
                recorder.start();
            }

            private void stopRecording() {
                recorder.stop();
                Toast.makeText(getApplicationContext(), "Enregistrement terminé", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
