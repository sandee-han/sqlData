package com.line.domain;

public class Hospital {
    private String id;
    private String address;
    private String district;
    private String category;
    private Integer emergencyRoom;  // snake camel
    private String name;

    private String subdivision;

    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }

    public String getCategory() {
        return category;
    }

    public Integer getEmergencyRoom() {
        return emergencyRoom;
    }

    public String getName() {
        return name;
    }

    public String getSubdivision() {
        return subdivision;
    }


    public Hospital(String id, String address, String category, Integer emergencyRoom, String name, String subdivision) {
        this.id = id;
        this.address = address;
        this.district = district;
        this.category = category;
        this.emergencyRoom = emergencyRoom;
        this.name = name;
        this.setDistrict();
        this.subdivision = subdivision;

    }

    private void setDistrict() {
        // 주소를 공백으로 나누고 주소의 앞 두개 (서울특별시 ㅇㅇ구) 리턴
        String[] splitted = this.address.split(" ");
        this.district = String.format("%s %s", splitted[0], splitted[1]);
    }


    public String getId() {
        return id;
    }


    public String getTupleString(){
        String sqlData = String.format("(\"%s\",\n" +
                        "\"%s\",\n" +
                        "\"%s\",\n" +
                        "\"%s\",\n" +
                        "%d,\n" +
                        "\"%s\",\n" +
                        "\"%s\"),\n",
                this.id, this.address, this.district, this.category, this.emergencyRoom, this.name, this.subdivision);
        return sqlData;
    }
}
