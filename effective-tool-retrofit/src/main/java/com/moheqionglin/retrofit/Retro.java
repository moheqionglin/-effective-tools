package com.moheqionglin.retrofit;


import com.moheqionglin.retrofit.message.Contributor;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.TlsVersion;
import retrofit2.Call;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
//https://futurestud.io/tutorials/retrofit-2-how-to-trust-unsafe-ssl-certificates-self-signed-expired
public class Retro {

    public static void main(String[] args) {
        GitHubClient client = ServiceGenerator.createService(GitHubClient.class);

        // Fetch and print a list of the contributors to this library.
        Call<List<Contributor>> call =
                client.contributors("lzyzsd", "Awesome-RxJava");

        List<Contributor> contributors = null;
        try {
            contributors = call.execute().body();
            for (Contributor contributor : contributors) {
                System.out.println(
                        contributor.getLogin() + " (" + contributor.getContributions() + ")");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main2(String[] args) {
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_0)
                .allEnabledCipherSuites()
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectionSpecs(Collections.singletonList(spec))
                .build();

        Request request = new Request.Builder()
                .url("https://192.168.0.19:44330")
                .build();

    }
}
