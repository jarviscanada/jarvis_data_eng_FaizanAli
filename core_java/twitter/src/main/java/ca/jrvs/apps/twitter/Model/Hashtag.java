package ca.jrvs.apps.twitter.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "text",
    "indices"
})

public class Hashtag {

  private String text;
  private int[] indices;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int[] getIndices() {
    return indices;
  }

  public void setIndices(int[] indices) {
    this.indices = indices;
  }
}
