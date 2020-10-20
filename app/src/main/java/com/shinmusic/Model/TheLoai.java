package com.shinmusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TheLoai implements Serializable {

@SerializedName("IdTheLoai")
@Expose
private String idTheLoai;
@SerializedName("TenTheLoai")
@Expose
private String tenTheLoai;
@SerializedName("ImageTheLoai")
@Expose
private String imageTheLoai;
@SerializedName("IdChuDe")
@Expose
private Object idChuDe;

public String getIdTheLoai() {
return idTheLoai;
}

public void setIdTheLoai(String idTheLoai) {
this.idTheLoai = idTheLoai;
}

public String getTenTheLoai() {
return tenTheLoai;
}

public void setTenTheLoai(String tenTheLoai) {
this.tenTheLoai = tenTheLoai;
}

public String getImageTheLoai() {
return imageTheLoai;
}

public void setImageTheLoai(String imageTheLoai) {
this.imageTheLoai = imageTheLoai;
}

public Object getIdChuDe() {
return idChuDe;
}

public void setIdChuDe(Object idChuDe) {
this.idChuDe = idChuDe;
}

}