package com.shinmusic.shinmusic.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaiHat implements Parcelable {

@SerializedName("IdBaiHat")
@Expose
private String idBaiHat;
@SerializedName("TenBaiHat")
@Expose
private String tenBaiHat;
@SerializedName("CaSy")
@Expose
private String caSy;
@SerializedName("ImageBaiHat")
@Expose
private String imageBaiHat;
@SerializedName("DuongDan")
@Expose
private String duongDan;
@SerializedName("LuotThich")
@Expose
private String luotThich;

    protected BaiHat(Parcel in) {
        idBaiHat = in.readString();
        tenBaiHat = in.readString();
        caSy = in.readString();
        imageBaiHat = in.readString();
        duongDan = in.readString();
        luotThich = in.readString();
    }

    public static final Creator<BaiHat> CREATOR = new Creator<BaiHat>() {
        @Override
        public BaiHat createFromParcel(Parcel in) {
            return new BaiHat(in);
        }

        @Override
        public BaiHat[] newArray(int size) {
            return new BaiHat[size];
        }
    };

    public String getIdBaiHat() {
return idBaiHat;
}

public void setIdBaiHat(String idBaiHat) {
this.idBaiHat = idBaiHat;
}

public String getTenBaiHat() {
return tenBaiHat;
}

public void setTenBaiHat(String tenBaiHat) {
this.tenBaiHat = tenBaiHat;
}

public String getCaSy() {
return caSy;
}

public void setCaSy(String caSy) {
this.caSy = caSy;
}

public String getImageBaiHat() {
return imageBaiHat;
}

public void setImageBaiHat(String imageBaiHat) {
this.imageBaiHat = imageBaiHat;
}

public String getDuongDan() {
return duongDan;
}

public void setDuongDan(String duongDan) {
this.duongDan = duongDan;
}

public String getLuotThich() {
return luotThich;
}

public void setLuotThich(String luotThich) {
this.luotThich = luotThich;
}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idBaiHat);
        parcel.writeString(tenBaiHat);
        parcel.writeString(caSy);
        parcel.writeString(imageBaiHat);
        parcel.writeString(duongDan);
        parcel.writeString(luotThich);
    }
}