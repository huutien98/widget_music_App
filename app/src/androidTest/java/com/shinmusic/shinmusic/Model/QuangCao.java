package com.shinmusic.shinmusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuangCao implements Serializable {

@SerializedName("IdQuangCao")
@Expose
private String idQuangCao;
@SerializedName("NoiDungQuangCao")
@Expose
private String noiDungQuangCao;
@SerializedName("HinhAnhQuangCao")
@Expose
private String hinhAnhQuangCao;
@SerializedName("IdBaiHat")
@Expose
private String idBaiHat;
@SerializedName("TenBaiHat")
@Expose
private String tenBaiHat;
@SerializedName("ImageBaiHat")
@Expose
private String imageBaiHat;

public String getIdQuangCao() {
return idQuangCao;
}

public void setIdQuangCao(String idQuangCao) {
this.idQuangCao = idQuangCao;
}

public String getNoiDungQuangCao() {
return noiDungQuangCao;
}

public void setNoiDungQuangCao(String noiDungQuangCao) {
this.noiDungQuangCao = noiDungQuangCao;
}

public String getHinhAnhQuangCao() {
return hinhAnhQuangCao;
}

public void setHinhAnhQuangCao(String hinhAnhQuangCao) {
this.hinhAnhQuangCao = hinhAnhQuangCao;
}

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

public String getImageBaiHat() {
return imageBaiHat;
}

public void setImageBaiHat(String imageBaiHat) {
this.imageBaiHat = imageBaiHat;
}

}