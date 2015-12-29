package com.zhg.api.samples.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.zhg.api.samples.R;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.http.FormUrlEncoded;

public class OkHttpTestActivity extends AppCompatActivity {

    @Bind(R.id.id_click)
    Button mClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_test);
        ButterKnife.bind(this);
        mClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doClick();
            }
        });

    }

    private void doClick() {

        OkHttpClient client=new OkHttpClient();
        try {
            setCertificates(client,getAssets().open(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        client.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ORIGINAL_SERVER));

        Request request=new Request.Builder().url("").build();

        FormEncodingBuilder formEncodingBuilder=new FormEncodingBuilder().add("username", "zhg");
        Request request1=new Request.Builder().url("").post(formEncodingBuilder.build()).build();

        RequestBody requestBody=RequestBody.create(MediaType.parse("application/octet-stream"), new File(""));
        RequestBody requestBody1=new MultipartBuilder().type(MultipartBuilder.FORM).addPart(Headers.of("Content-Disposition", "form-data;name=\"username\""), RequestBody.create(null, "zhg"))
                .addPart(Headers.of("Content-Disposition","form-data;name=\"mFile\";filename=\"test.mp4\""),requestBody)
                .build();
        Request request2=new Request.Builder().url("").post(requestBody1).build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }

    private void setCertificates(OkHttpClient client,InputStream clientKeyFile, InputStream... certificates) {
        try {
            CertificateFactory certificateFactory=CertificateFactory.getInstance("X.509");
            KeyStore keyStore=KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index=0;
            for(InputStream certificate:certificates){
                String alias=Integer.toString(index++);
                keyStore.setCertificateEntry(alias,certificateFactory.generateCertificate(certificate));
            }
            TrustManagerFactory trustManagerFactory=TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            KeyStore clientKeyStore=KeyStore.getInstance("BKS");
            clientKeyStore.load(clientKeyFile, "123456".toCharArray());
            KeyManagerFactory keyManagerFactory=KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(clientKeyStore, "123456".toCharArray());
            SSLContext sslContext=SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
            client.setSslSocketFactory(sslContext.getSocketFactory());
//            client.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            client.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

}
