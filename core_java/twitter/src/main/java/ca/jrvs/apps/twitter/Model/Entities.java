package ca.jrvs.apps.twitter.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "hashtags",
    "user_mentions"
})

public class Entities {

  private List<Hashtag> hashtags;

  @JsonProperty("user_mentions")
  private List<UserMention> userMentions;


  public List<Hashtag> getHashtags() {
    return hashtags;
  }

  public void setHashtags(List<Hashtag> hashtags) {
    this.hashtags = hashtags;
  }

  @JsonProperty("user_mentions")
  public List<UserMention> getUserMentions() {
    return userMentions;
  }

  @JsonProperty("user_mentions")
  public void setUserMentions(List<UserMention> userMentions) {
    this.userMentions = userMentions;
  }
}
