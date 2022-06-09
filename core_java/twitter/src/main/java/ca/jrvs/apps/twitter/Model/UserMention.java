package ca.jrvs.apps.twitter.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "indices",
    "screen_name",
    "id",
    "id_str"
})
public class UserMention {

  private String name;
  private int[] indices;

  @JsonProperty("screen_name")
  private String screenName;

  private long id;

  @JsonProperty("id_str")
  private String idStr;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int[] getIndices() {
    return indices;
  }

  public void setIndices(int[] indices) {
    this.indices = indices;
  }

  @JsonProperty("screen_name")
  public String getScreenName() {
    return screenName;
  }

  @JsonProperty("screen_name")
  public void setScreenName(String screenName) {
    this.screenName = screenName;
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
}
