package br.com.newmallapp3.newmall.dev.api;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import br.com.newmallapp3.newmall.dev.entity.AccessToken;
import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.provider.Settings.System.DATE_FORMAT;

public class ServiceGenerator {

    public static final String API_BASE_URL = "http://newmallservice.azurewebsites.net";
    private static OkHttpClient.Builder httpClient;
    private static Retrofit.Builder builder;
    private static Context mContext;
    private static AccessToken mToken;

    public static <S> S createService(Class<S> serviceClass) {
        httpClient = new OkHttpClient.Builder();
        builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    static class DateDeserializer implements JsonDeserializer<Date> {

        private final String TAG = DateDeserializer.class.getSimpleName();

        @Override
        public Date deserialize(JsonElement element, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            String date = element.getAsString();

            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date returnDate = null;
            try {
                returnDate = formatter.parse(date);
            } catch (ParseException e) {
                Log.e(TAG, "Date parser exception:", e);
                returnDate = null;
            }
            return returnDate;
        }
    }

    static class DateSerializer implements JsonSerializer<Date> {

        private final String TAG = DateSerializer.class.getSimpleName();

        @Override
        public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            formatter.setTimeZone(TimeZone.getDefault());
            String dateFormatAsString = formatter.format(date);
            return new JsonPrimitive(dateFormatAsString);
        }

    }





}
