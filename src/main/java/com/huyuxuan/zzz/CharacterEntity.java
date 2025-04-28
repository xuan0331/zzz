package com.huyuxuan.zzz;

public class CharacterEntity {
    private Integer id;
    private String name;
    private String weaponName;
    private String artifactsName;
    private String introduce;
    private String imageUrl;
    private Integer provideHp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getArtifactsName() {
        return artifactsName;
    }

    public void setArtifactsName(String artifactsName) {
        this.artifactsName = artifactsName;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getProvideHp() {
        return provideHp;
    }

    public void setProvideHp(Integer provideHp) {
        this.provideHp = provideHp;
    }
}
