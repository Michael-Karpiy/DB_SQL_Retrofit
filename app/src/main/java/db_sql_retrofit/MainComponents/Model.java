package db_sql_retrofit.MainComponents;

import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("position") private String position;
    @SerializedName("id") private String id;
    @SerializedName("name") private String name;
    @SerializedName("description") private String description;

    public String getPosition() {return position;}
    public void setPosition(String position) {this.position = position;}

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
}
