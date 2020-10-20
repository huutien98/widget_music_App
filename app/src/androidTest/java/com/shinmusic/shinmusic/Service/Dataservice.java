package com.shinmusic.shinmusic.Service;

import com.shinmusic.shinmusic.Model.Album;
import com.shinmusic.shinmusic.Model.BaiHat;
import com.shinmusic.shinmusic.Model.ChuDe;
import com.shinmusic.shinmusic.Model.QuangCao;
import com.shinmusic.shinmusic.Model.TheLoai;
import com.shinmusic.shinmusic.Model.TheLoaiTrongNgay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("Server/songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("Server/chudevatheloai.php")
    Call<TheLoaiTrongNgay> GetCategoryMusic();

    @GET("Server/album.php")
    Call<List<Album>> GetAlbum();

    @GET("Server/baihatyeuthich.php")
    Call<List<BaiHat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("Server/danhsachbaihat.php")
    Call<List<BaiHat>> GetDSBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("Server/danhsachbaihat.php")
    Call<List<BaiHat>> GetDSBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @GET("Server/tatcachude.php")
    Call<List<ChuDe>> GetAllChuDe();

    @FormUrlEncoded
    @POST("Server/theloaitheochude.php")
    Call<List<TheLoai>> GetTheLoaiTheoChuDe(@Field("idchude") String idchude);

    @FormUrlEncoded
    @POST("Server/updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);


}
