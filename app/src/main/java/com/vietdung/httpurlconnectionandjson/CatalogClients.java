package com.vietdung.httpurlconnectionandjson;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CatalogClients extends AsyncTask<String, String, List<User>> {
    public static final int SPARAM_URL = 0;
    public static final String SKEY_NAME = "name";
    public static final String SKEY_FULL_NAME = "full_name";
    private String mRequestMethod = "GET";
    private List<User> mUsers;
    private onTaskListerner mTaskCompele;

    public CatalogClients(onTaskListerner taskCompele) {
        mTaskCompele = taskCompele;
    }

    @Override
    protected List<User> doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(params[SPARAM_URL]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(mRequestMethod);
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String responseString = readStream(urlConnection.getInputStream());
                mUsers = parseUserData(responseString);
            }
        } catch (Exception e) {

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return mUsers;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    @Override
    protected void onPostExecute(List<User> users) {
        super.onPostExecute(users);
        mTaskCompele.onTaskCompletion(mUsers);
    }

    private List<User> parseUserData(String jString) {
        List<User> users = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jString);
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString(SKEY_NAME);
                    String full_name = jsonObject.getString(SKEY_FULL_NAME);
                    users.add(new User(name, full_name));
                }
            }
        } catch (Exception e) {

        }
        return users;
    }
}
