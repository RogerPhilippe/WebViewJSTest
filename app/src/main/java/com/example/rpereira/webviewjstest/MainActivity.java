package com.example.rpereira.webviewjstest;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.Manifest.permission.INTERNET;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextURL;

    private Button mBtnIr;

    private WebView mWebView;

    private String encodedHtml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

        // Create an unencoded HTML string
        // then convert the unencoded HTML string into bytes, encode
        // it with Base64, and load the data.
        //String unencodedHtml = "&lt;html&gt;&lt;body&gt;'%23' is the percent code for # &lt;/body&gt;&lt;/html&gt;";
        //String unencodedHtml = "<html><body><a href=\"http://google.com\">Google</a></body></html>";
        // Obs.: É necessário deixar \n.
        String unencodedHtml =
                "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "<title>chart created with amCharts | amCharts</title>\n" +
                        "<meta name=\"description\" content=\"chart created using amCharts live editor\" />\n" +
                        "<!-- amCharts javascript sources -->\n" +
                        "<script type=\"text/javascript\" src=\"https://www.amcharts.com/lib/3/amcharts.js\"></script>\n" +
                        "<script type=\"text/javascript\" src=\"https://www.amcharts.com/lib/3/serial.js\"></script>\n" +
                        "<!-- amCharts javascript code -->\n" +
                        "<script type=\"text/javascript\">\n" +
                        "AmCharts.makeChart(\"chartdiv\",\n" +
                        "{\n" +
                        "\"type\": \"serial\",\n" +
                        "\"categoryField\": \"category\",\n" +
                        "\"startDuration\": 1,\n" +
                        "\"categoryAxis\": {\n" +
                        "\"gridPosition\": \"start\"\n" +
                        "},\n" +
                        "\"trendLines\": [],\n" +
                        "\"graphs\": [\n" +
                        "{\n" +
                        "\"balloonText\": \"[[title]] of [[category]]:[[value]]\",\n" +
                        "\"fillAlphas\": 1,\n" +
                        "\"id\": \"AmGraph-1\",\n" +
                        "\"title\": \"graph 1\",\n" +
                        "\"type\": \"column\",\n" +
                        "\"valueField\": \"column-1\"\n" +
                        "}\n" +
                        "],\n" +
                        "\"guides\": [],\n" +
                        "\"valueAxes\": [\n" +
                        "{\n" +
                        "\"id\": \"ValueAxis-1\",\n" +
                        "\"title\": \"Axis title\"\n" +
                        "}\n" +
                        "],\n" +
                        "\"allLabels\": [],\n" +
                        "\"balloon\": {},\n" +
                        "\"legend\": {\n" +
                        "\"enabled\": true,\n" +
                        "\"useGraphSettings\": true\n" +
                        "},\n" +
                        "\"titles\": [\n" +
                        "{\n" +
                        "\"id\": \"Title-1\",\n" +
                        "\"size\": 15,\n" +
                        "\"text\": \"Chart Title\"\n" +
                        "}\n" +
                        "],\n" +
                        "\"dataProvider\": [\n" +
                        "{\n" +
                        "\"category\": \"category 1\",\n" +
                        "\"column-1\": 8\n" +
                        "},\n" +
                        "{\n" +
                        "\"category\": \"category 2\",\n" +
                        "\"column-1\": 6\n" +
                        "},\n" +
                        "{\n" +
                        "\"category\": \"category 3\",\n" +
                        "\"column-1\": 2\n" +
                        "}\n" +
                        "]\n" +
                        "}\n" +
                        ");\n" +
                        "</script>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "<div id=\"chartdiv\" style=\"width: 100%; height: 400px; background-color: #FFFFFF;\" ></div>\n" +
                        "</body>\n" +
                        "</html>";

        encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(),
                Base64.NO_PADDING);

        mEditTextURL = (EditText) findViewById(R.id.idEditTextURL);

        mBtnIr = (Button) findViewById(R.id.idBtnIr);

        mWebView = (WebView) findViewById(R.id.idWebView);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mBtnIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(MainActivity.this, INTERNET) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                if (!mEditTextURL.getText().toString().isEmpty()) {
                    if (mEditTextURL.getText().toString().equals("test")) {
                        mWebView.loadData(encodedHtml, "text/html", "base64");
                    } else {
                        mWebView.loadUrl(mEditTextURL.getText().toString());
                    }
                } else {
                    Toast.makeText(MainActivity.this, "URL está vazia!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[] {INTERNET}, 1);
    }
}
