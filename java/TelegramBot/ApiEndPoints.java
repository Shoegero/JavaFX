package TelegramBot;

import TelegramBot.model.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApiEndPoints {
    @GET("animes")
    Call<List<RootAnime>> searchAnime(
            @Query("search") String search
    );

    @GET("mangas")
    Call<List<RootManga>> searchManga(
            @Query("search") String search
    );

    @GET("characters/search")
    Call<List<RootCharacter>> searchCharacter(
            @Query("search") String search
    );

    @GET("users/102048/anime_rates")
    Call<List<RootWatchList>> searchAnimeUrl(
            @Query("page") Integer page,
            @Query("limit") Integer limit
    );

    @POST("v2/user_rates")
    Call<List<RootRate>> changeRating(
            @Body RootAnime rootAnime
    );

    @POST("messages")
    Call<List<RootMessages>> sendDm(
            @Body RootAnime rootAnime
    );

    @GET("users/{user}/?is_nickname=1")
    Call<RootUser> getId(
            @Path("user") String user
    );
}
