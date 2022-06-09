package ca.jrvs.apps.twitter.Model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "created_at",
    "id",
    "id_str",
    "text",
    "entities",
    "coordinates",
    "retweet_count",
    "favorite_count",
    "favorited",
    "retweeted"
})

public class Tweet {

  @JsonProperty("created_at")
  private String createdAt;

  private long id;

  @JsonProperty("id_str")
  private String idStr;

  private String text;

  private Entities entities;
  private Coordinates coordinates;

  @JsonProperty("retweet_count")
  private int retweetCount;

  @JsonProperty("favorite_count")
  private int favoriteCount;

  private boolean favorited;
  private boolean retweeted;


  @JsonProperty("created_at")
  public String getCreatedAt() {
    return createdAt;
  }

  @JsonProperty("created_at")
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @JsonProperty("id_str")
  public String getIdStr() {
    return idStr;
  }

  @JsonProperty("id_str")
  public void setIdStr(String idStr) {
    this.idStr = idStr;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Entities getEntities() {
    return entities;
  }

  public void setEntities(Entities entities) {
    this.entities = entities;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  @JsonProperty("retweet_count")
  public int getRetweetCount() {
    return retweetCount;
  }

  @JsonProperty("retweet_count")
  public void setRetweetCount(int retweetCount) {
    this.retweetCount = retweetCount;
  }

  @JsonProperty("favorite_count")
  public int getFavoriteCount() {
    return favoriteCount;
  }

  @JsonProperty("favorite_count")
  public void setFavoriteCount(int favoriteCount) {
    this.favoriteCount = favoriteCount;
  }

  public boolean isFavorited() {
    return favorited;
  }

  public void setFavorited(boolean favorited) {
    this.favorited = favorited;
  }

  public boolean isRetweeted() {
    return retweeted;
  }

  public void setRetweeted(boolean retweeted) {
    this.retweeted = retweeted;
  }


}
